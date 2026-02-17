INSERT INTO users (name, email, password, role, created_at)
VALUES ('Umair Ali', 'umair.ali@example.com', 'hashed_password', 'USER', NOW());

INSERT INTO short_urls
(short_key, original_url, created_by, created_at, expires_at, is_private, click_count)
VALUES
('rs1Aed', 'https://www.netflix.com', 1, '2024-07-15 00:00:00', NULL, 0, 0),
('hujfDf', 'https://www.youtube.com', 1, '2024-07-16 00:00:00', NULL, 0, 0),
('ertcbn', 'https://www.google.com', 1, '2024-07-17 00:00:00', NULL, 0, 0),
('edfrtg', 'https://www.hotstar.com', 1, '2024-07-18 00:00:00', NULL, 1, 0),
('vbgtyh', 'https://www.amazon.in', 1, '2024-07-19 00:00:00', NULL, 0, 0),
('rtyfgb', 'https://www.github.com', 1, '2024-07-25 00:00:00', NULL, 0, 0),
('rtvbop', 'https://www.stackoverflow.com', 1, '2024-07-26 00:00:00', NULL, 0, 0),
('2wedfg', 'https://www.linkedin.com', 1, '2024-07-27 00:00:00', NULL, 1, 0),
('6yfrd4', 'https://www.microsoft.com', 1, '2024-07-28 00:00:00', NULL, 0, 0),
('r5t4tt', 'https://www.apple.com', 1, '2024-07-29 00:00:00', NULL, 0, 0),

('ffr4rt', 'https://www.spotify.com', 1, '2024-08-10 00:00:00', NULL, 0, 0),
('9oui7u', 'https://www.twitter.com', 1, '2024-08-11 00:00:00', NULL, 0, 0),
('cvbg5t', 'https://www.facebook.com', 1, '2024-08-12 00:00:00', NULL, 0, 0),
('nm6ytf', 'https://www.instagram.com', 1, '2024-08-13 00:00:00', NULL, 0, 0),

('tt5y6r', 'https://www.flipkart.com', 1, '2024-08-14 00:00:00', NULL, 0, 0),
('fgghty', 'https://www.paytm.com', 1, '2024-08-15 00:00:00', NULL, 0, 0),
('f45tre', 'https://www.udemy.com', 1, '2024-08-16 00:00:00', NULL, 0, 0),
('54rt54', 'https://www.cloudflare.com', 1, '2024-08-17 00:00:00', NULL, 0, 0);
