CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bio` varchar(160) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `gender` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','JOURNALIST','USER') DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

