CREATE TABLE `tb_comment_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `comment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbp7lfnipsp0brlka8ang2fj2n` (`author_id`),
  KEY `FKt9e675acs3qnl4w6p6bm4xlor` (`comment_id`),
  CONSTRAINT `FKbp7lfnipsp0brlka8ang2fj2n` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKt9e675acs3qnl4w6p6bm4xlor` FOREIGN KEY (`comment_id`) REFERENCES `tb_comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

