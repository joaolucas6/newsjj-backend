CREATE TABLE `news_topics` (
  `topic_id` bigint NOT NULL,
  `news_id` bigint NOT NULL,
  KEY `FKam2hsbqhx9cx835xvda4x6x2j` (`news_id`),
  KEY `FKh8v49gdau58jsbxlebfih6lsi` (`topic_id`),
  CONSTRAINT `FKam2hsbqhx9cx835xvda4x6x2j` FOREIGN KEY (`news_id`) REFERENCES `tb_news` (`id`),
  CONSTRAINT `FKh8v49gdau58jsbxlebfih6lsi` FOREIGN KEY (`topic_id`) REFERENCES `tb_topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

