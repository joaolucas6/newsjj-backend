CREATE TABLE `followers` (
  `followed_id` bigint NOT NULL,
  `follower_id` bigint NOT NULL,
  KEY `FKr1t39v8r1vkt5ars487bhht6u` (`follower_id`),
  KEY `FKkhfcysbh5unrl9worajktnmh` (`followed_id`),
  CONSTRAINT `FKkhfcysbh5unrl9worajktnmh` FOREIGN KEY (`followed_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKr1t39v8r1vkt5ars487bhht6u` FOREIGN KEY (`follower_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

