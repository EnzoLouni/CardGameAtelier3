CREATE DATABASE card_manager;
\c card_manager;

CREATE TABLE public.card (
  id          serial,
  name        varchar(50),
  description	varchar(200),
  family	    varchar(20),
  affinity	varchar(30),
  img_url	    varchar(200),
  small_img_url	varchar(200),
  energy	    float,
  hp	        float,
  defense	    float,
  attack	    float,
  price	    float,
  user_id     integer,
  CONSTRAINT PK_CARD PRIMARY KEY (id)
);

INSERT INTO public.card (name, description, family, affinity, img_url, small_img_url, energy, hp, defense, attack, price)
VALUES
    ('Zozz enragé', 'Cette carte représente un Zozzer enragé.', 'Zozzer', 'Terre', 'img/zozz_enrage.png', 'img/small/zozz_aenrage.png', 1.5, 80, 70, 50, 2.99),
    ('Zozz anti-germanique', 'Le Zozz anti-germanique est un Zozz spécialisé dans la lutte contre les Zozzers de type germanique.', 'Zozz', 'Terre', 'img/zozz_anti_germanique.png', 'img/small/zozz_anti_germanique.png', 2.5, 90, 80, 70, 5.99),
    ('ZéoZolitique', 'Le ZéoZolitique est un Zozz influencé par la géopolitique.', 'Zozz', 'Feu', 'img/zeozolitique.png', 'img/small/zeozolitique.png', 3.0, 70, 60, 80, 6.99),
    ('Zozzermanique', 'Le Zozzermanique est un Zozzer de type germanique, puissant et agressif.', 'Zozzer', 'Eau', 'img/zozzgermanique.png', 'img/small/zozzgermanique.png', 2.5, 80, 70, 60, 5.99),
    ('Zozz chef de projet', 'Le Zozz chef de projet est un Zozz spécialisé dans la gestion de projets complexes. Il a été commandité par le Zozz anti-germanique pour couler une dalle stratégique.', 'Zozz', 'Air', 'img/zozz_chef_de_projet.png', 'img/small/zozz_chef_de_projet.png', 3.5, 60, 40, 50, 7.99),
    ('Zozzers du bâtiment', 'Les Zozzers du bâtiment sont des employés du Zozz chef de projet spécialisés dans la construction et le travail manuel.', 'Zozzer', 'Terre', 'img/zozzers_du_batiment.png', 'img/small/zozzers_du_batiment.png', 2.0, 50, 60, 70, 4.99),
    ('Zozz cynique', 'Le Zozz cynique est connu pour son attitude sarcastique et son sens de l''ironie. Son attaque est réduite, car il préfère se moquer de ses adversaires plutôt que de les attaquer directement.', 'Zozz', 'Feu', 'img/zozz_cynique.png', 'img/small/zozz_cynique.png', 2.0, 60, 30, 50, 4.99),
    ('Zhistorien', 'Le Zhistorien est un Zozz passionné d''histoire et de connaissances.', 'Zozz', 'Eau', 'img/zhistorien.png', 'img/small/zhistorien.png', 2.5, 75, 40, 60, 5.99),
    ('Zozzer ultime - MaTTHIas', 'MaTTHIas est le Zozzer ultime, représentant la puissance et la perfection dans le monde de Zozzémon.', 'Zozzer', 'Air', 'img/zozzer_ultime.png', 'img/small/zozzer_ultime.png', 5.0, 100, 100, 100, 100),
    ('Néo-Zozzer - Raphaël', 'Raphaël est un Néo-Zozzer, une forme évoluée et améliorée de Zozzer dans le monde de Zozzémon. ', 'Zozzer', 'Terre', 'img/neo_zozzer.png', 'img/small/neo_zozzer.png', 3.5, 80, 75, 60, 8.99),
    ('Anti-Zozzer - Mathis', 'Mathis est un Anti-Zozzer, spécialisé dans la lutte contre les Zozzers adversaires.', 'Zozzer', 'Feu', 'img/anti_zozzer.png', 'img/small/anti_zozzer.png', 3.0, 85, 80, 75, 7.99),
    ('Fidèle Zozzer - Thony', 'Thony est un Fidèle Zozzer, dévoué et loyal envers ses alliés.', 'Zozzer', 'Eau', 'img/fidele_zozzer.png', 'img/small/fidele_zozzer.png', 2.5, 70, 60, 80, 6.99),
    ('ZAlexis l''allergique - le Bref', 'ZAlexis l''allergique, surnommé le Bref, est un Zozz qui souffre d''allergies sévères.', 'Zozzer', 'Air', 'img/zalexis_allergique.png', 'img/small/zalexis_allergique.png', 2.0, 60, 50, 40, 4.99),
    ('ZAlexis le Gros - les 60€', 'ZAlexis le Gros, surnommé les 60€, est un Zozz imposant et puissant.', 'Zozzer', 'Terre', 'img/zalexis_gros.png', 'img/small/zalexis_gros.png', 2.5, 100, 80, 60, 5.99),
    ('Le Zozz', 'Le Zozz est l''un des Zozzers de base dans le monde de Zozzémon.', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png', 3.0, 80, 70, 60, 6.99),
    ('Zozz le pro de git', 'Zozz, le Pro de Git, est un expert dans l''utilisation du versionnement et du contrôle de code avec Git.', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png', 3.0, 70, 60, 70, 6.99),
    ('Zactiviste', 'Zactiviste, est un fervent défenseur des droits et des causes sociales.', 'Zozz', 'Eau', 'img/zozz_activiste.png', 'img/small/zozz_activiste.png', 2.5, 85, 50, 90, 5.99),
    ('Zozz - Barrage à Zacron', 'Zozz, le Barrage à Zacron, est un Zozz déterminé à contrecarrer les plans de Zacron, un puissant antagoniste dans le monde de Zozzémon.', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png', 3.0, 85, 70, 65, 6.99),
    ('Zacron - Le Visionnaire', 'Zacron, surnommé Le Visionnaire, est un Zozz charismatique et habile dans la prise de décisions. Capable de motiver son équipe et d''adopter des approches diplomatiques pour gagner les combats.', 'Zacron', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png', 3.0, 80, 70, 70, 6.99);