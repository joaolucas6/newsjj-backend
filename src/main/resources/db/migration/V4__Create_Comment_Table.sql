CREATE TABLE `tb_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `text` varchar(280) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `news_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeatlmflrjsnvay1uuk75wwf08` (`author_id`),
  KEY `FKsr5cyxovk75tfy6q4a26samu1` (`news_id`),
  CONSTRAINT `FKeatlmflrjsnvay1uuk75wwf08` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKsr5cyxovk75tfy6q4a26samu1` FOREIGN KEY (`news_id`) REFERENCES `tb_news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

