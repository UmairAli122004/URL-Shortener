package com.UrlShortener.Mapper;

import com.UrlShortener.DTOs.ShortUrlDto;
import com.UrlShortener.DTOs.UserDto;
import com.UrlShortener.Entities.ShortUrl;
import com.UrlShortener.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public ShortUrlDto toShortUrlDto(ShortUrl shortUrl){
        UserDto userDto = null;
        if(shortUrl.getCreatedBy() != null){
            userDto = toUserDto(shortUrl.getCreatedBy());
        }
        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortKey(),
                shortUrl.getOriginalUrl(),
                shortUrl.getIsPrivate(),
                shortUrl.getExpiresAt(),
                userDto,
                shortUrl.getClickCount(),
                shortUrl.getCreatedAt()
        );
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getName());
    }
}
