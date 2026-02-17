package com.UrlShortener.Services;

import com.UrlShortener.ApplicationProperties;
import com.UrlShortener.DTOs.CreateShortUrlCmd;
import com.UrlShortener.DTOs.PagedResult;
import com.UrlShortener.DTOs.ShortUrlDto;
import com.UrlShortener.Entities.ShortUrl;
import com.UrlShortener.Mapper.EntityMapper;
import com.UrlShortener.Repositories.ShortUrlRepository;
import com.UrlShortener.Repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final EntityMapper entityMapper;
    private final ApplicationProperties properties;
    private final UserRepository userRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, EntityMapper entityMapper,ApplicationProperties properties,UserRepository userRepository){
        this.shortUrlRepository = shortUrlRepository;
        this.entityMapper = entityMapper;
        this.properties = properties;
        this.userRepository=userRepository;
    }

    public PagedResult<ShortUrlDto> findPublicShortUrls(int pageNo, int pageSize){
        Pageable pageable = getPageable(pageNo, pageSize);
        var shortUrlPage = shortUrlRepository.findPublicShortUrls(pageable).map(entityMapper::toShortUrlDto);
        return PagedResult.from(shortUrlPage);
    }

    public PagedResult<ShortUrlDto> getUserShortUrls(Long userId, int page, int pageSize) {
        Pageable pageable = getPageable(page, pageSize);
        var shortUrlsPage = shortUrlRepository.findByCreatedById(userId, pageable)
                .map(entityMapper::toShortUrlDto);
        return PagedResult.from(shortUrlsPage);
    }

    @Transactional
    public void deleteUserShortUrls(List<Long> ids, Long userId) {
        if (ids != null && !ids.isEmpty() && userId != null) {
            shortUrlRepository.deleteByIdInAndCreatedById(ids, userId);
        }
    }

    private Pageable getPageable(int page, int size){
        page= page > 1 ? page-1 : 0;
        return PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
    }

    @Transactional
    public ShortUrlDto createShortUrl(CreateShortUrlCmd cmd) {
        if(properties.validateOriginalUrl()) {
            boolean urlExists = UrlExistenceValidator.isUrlExists(cmd.originalUrl());
            if(!urlExists) {
                throw new RuntimeException("Invalid URL "+cmd.originalUrl());
            }
        }
        var shortKey = generateUniqueShortKey();
        var shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(cmd.originalUrl());
        shortUrl.setShortKey(shortKey);
        if(cmd.userId()==null){
            shortUrl.setCreatedBy(null);
            shortUrl.setIsPrivate(false);
            shortUrl.setExpiresAt(LocalDateTime.now().plusDays(properties.defaultExpiryInDays()));
        }else{
            shortUrl.setCreatedBy(userRepository.findById(cmd.userId()).orElseThrow());
            shortUrl.setIsPrivate(cmd.isPrivate() != null && cmd.isPrivate());
            shortUrl.setExpiresAt(cmd.expirationIbDays() != null ? LocalDateTime.now().plusDays(cmd.expirationIbDays()) : null);
        }

        shortUrl.setClickCount(0L);
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrlRepository.save(shortUrl);
        return entityMapper.toShortUrlDto(shortUrl);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = generateRandomShortKey();
        } while (shortUrlRepository.existsByShortKey(shortKey));
           return shortKey;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_KEY_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomShortKey() {
        StringBuilder sb = new StringBuilder(SHORT_KEY_LENGTH);
        for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @Transactional
    public Optional<ShortUrlDto> accessShortUrl(String shortKey, Long userId) {
          Optional<ShortUrl> shortUrlOptional =  shortUrlRepository.findByShortKey(shortKey);
          if(shortUrlOptional.isEmpty()){
              return Optional.empty();
          }

          ShortUrl shortUrl = shortUrlOptional.get();
          if(shortUrl.getExpiresAt() != null && shortUrl.getExpiresAt().isBefore(LocalDateTime.now())){
              return Optional.empty();
          }
          if(shortUrl.getIsPrivate() != null && shortUrl.getCreatedBy() != null
                  && !Objects.equals(shortUrl.getCreatedBy().getId(), userId)){
              return Optional.empty();
          }
          shortUrl.setClickCount(shortUrl.getClickCount()+1);
          shortUrlRepository.save(shortUrl);
          return shortUrlOptional.map(entityMapper::toShortUrlDto);
    }
}
