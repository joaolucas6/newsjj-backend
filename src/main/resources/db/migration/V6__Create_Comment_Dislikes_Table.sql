CREATE TABLE `tb_comment_dislike` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instant` datetime(6) DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `comment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3wflnqqk2kqguh84bo7bpvsce` (`author_id`),
  KEY `FKkxfr91dg89y7obfur7w51u1g` (`comment_id`),
  CONSTRAINT `FK3wflnqqk2kqguh84bo7bpvsce` FOREIGN KEY (`author_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKkxfr91dg89y7obfur7w51u1g` FOREIGN KEY (`comment_id`) REFERENCES `tb_comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

