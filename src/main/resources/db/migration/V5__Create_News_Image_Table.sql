CREATE TABLE `news_images_url` (
  `news_id` bigint NOT NULL,
  `images_url` varchar(255) DEFAULT NULL,
  KEY `FKvwt7gh6kpo9yyl76dl60rufe` (`news_id`),
  CONSTRAINT `FKvwt7gh6kpo9yyl76dl60rufe` FOREIGN KEY (`news_id`) REFERENCES `tb_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

