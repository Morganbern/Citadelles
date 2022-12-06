package modele;

public class Caracteristiques {
	public static String REGLES=
		"Chaque joueur est à la tête d’une cité qu’il doit développer pour la rendre plus riche et plus\r\n"
		+ "prestigieuse. Les quartiers des cités sont représentés par des cartes. Lorsqu’un joueur place le\r\n"
		+ "huitième quartier de sa cité, on finit le tour et la partie est terminée.\r\n"
		+ "Au début du jeu, chaque joueur reçoit quatre cartes de quartier, deux pièces d’or et une carte\r\n"
		+ "d’aide de jeu. Un joueur, tiré au sort, reçoit la carte \" Couronne \".\r\n\r\n"
		+ "Les cartes de quartier :\r\n"
		+ "Chaque quartier a, en bas à gauche, une gemme de couleur qui indique sa catégorie (Religion =\r\n"
		+ "bleu, Noblesse = jaune, Commerce = vert, Soldatesque = rouge, Prestige = violet).\r\n"
		+ "Chaque quartier a un coût de construction égal au nombre de pièces d’or figurant en haut à gauche\r\n"
		+ "de la carte. Chaque quartier violet, ou Merveille, a un effet sur le jeu, en faveur du joueur dans la\r\n"
		+ "cité duquel il est construit, qui est décrit sur la carte.\r\n\r\n"
		+ "Déroulement d’un tour de jeu :\r\n"
		+ "Chaque tour se compose de deux phases distinctes. Les joueurs, en sens horaire, choisissent tout\r\n"
		+ "d’abord chacun un Personnage. Chacun joue ensuite à son tour, dans l’ordre des numéros des\r\n"
		+ "personnages.\r\n\r\n";
	
	public static String ASSASSIN= 
		"L'assassin peut tuer un personnage de son choix. " +
		"Celui-ci ne jouera pas ce tour-ci.";
	public static String ECHEVIN= 
		"L'�chevin place des mandats de r�quisition, face cach�e, " +
		"sous 3 jetons Personnage diff�rents. Il peut r�v�ler le " +
		"mandat sign� pour confisquer le premier quartier b�ti par " +
		"le joueur cibl�, qui r�cup�re le co�t de construction.";
	public static String SORCIERE=
		"Apr�s avoir pris or ou carte, la Sorci�re ensorcelle un " +
		"personnage et interrompt son tour. Elle le terminera en " +
		"jouant � la place du personnage ensorcel�";
	public static String MAITRE_CHANTEUSE=
		"La Ma�tre-chanteuse place des menaces, face cach�e, sous " +
		"2 jetons Personnage diff�rents. Un personnage menac� peut " +
		"lui payer la moiti� de son or pour retirer la menace. Si " +
		"la Ma�tre-chanteuse r�v�le le vrai jeton Menace, elle " +
		"prend tout l'or du joueur cibl�";
	public static String ESPION=
		"L'Espion choisit un type (couleur) de quartier et regarde " +
		"la main d'un autre joueur. Pour chaque quartier du type " +
		"indiqu�, il re�oit 1 pi�ce d'or de ce joueur et pioche 1 " +
		"carte Quartier.";
	public static String VOLEUR=
		"Le Voleur peut piller le tr�sor du personnage de son choix. " +
		"Quand le personnage d�trouss� sera r�v�l�, il donnera tout " +
		"son or au Voleur.";
	public static String MAGICIENNE=
		"La Magicienne peut, au choix : soit �changer la totalit� " +
		"des cartes de sa main avec celle d'un autre joueur, soit " +
		"�changer des cartes de sa main contre le m�me nombre de " +
		"cartes de la pioche.";
	public static String SORCIER=
		"Le Sorcier choisit 1 carte dans la main d'un autre joueur " +
		"et peut soit payer son co�t pour la b�tir, soit l'ajouter " +
		"� sa main. Il peut b�tir des quartiers identiques � "+
		"d'autres quartiers de sa cit�.";
	public static String VOYANTE=
		"La Voyante prend 1 carte au hasard dans la main de chaque " +
		"joueur, puis donne � chacun 1 carte de son choix. Son " +
		"permis de construire est de 2 quartiers.";
	public static String EMPEREUR=
		"L'Empereur donne la Couronne � un autre joueur, qui lui " +
		"paie 1 pi�ce d'or ou 1 carte. Chaque quartier Noble dans " +
		"sa cit� lui rapporte 1 pi�ce d'or.";
	public static String PATRICIEN=
		"Le Patricien prend la Couronne et choisira en premier " +
		"en premier son Personnage lors du prochain tour. " +
		"Chaque quartier Noble dans sa cit� lui rapporte 1 " +
		"pi�ce d'or.";
	public static String ROI = 
		"Le Roi prend la Couronne et choisira " +
		"en premier son personnage lors du prochain tour." +
		"Chaque quartier Noble dans sa cit� lui rapporte 1 " +
		"pi�ce d'or.";
	public static String ABBE=
		"Le joueur le plus riche doit donner 1 pi�ce d'or � l'Abb�." +
		"Chaque quartier Religieux dans sa cit� lui rapport 1 pi�ce " +
		"d'or ou 1 carte.";
	public static String CARDINAL=
		"Si le Cardinal n'a pas assez d'or pour b�tir un quartier, " +
		"il peut le prendre � un joueur en �change de carte (1 " +
		"carte = 1 pi�ce d'or). Chaque quartier Religieux dans sa " +
		"cit� lui rapport 1 carte quartier.";
	public static String EVEQUE=
		"La cit� de l'Ev�que est prot�g�e contre les personnages " +
		"de rang 8 (gemme rouge). Chaque quartier Religieux dans sa "+
		"cit� lui rapport 1 pi�ce d'or";
	public static String ALCHIMISTE=
		"A la fin de son tour, l'Alchimiste r�cup�re l'or qu'il a " +
		"d�pens� pour b�tir des quartiers durant son tour.";
	public static String MARCHANDE=
		"La Marchande re�oit 1 pi�ce d'or suppl�mentaire. Chaque " +
		"quartier Commer�ant dans sa cit� lui rapporte 1 pi�ce d'or.";
	public static String NEGOCIANT=
		"Le N�gociant peut b�tir des quartiers Commer�ants sans " +
		"restrictions. Chaque quartier Commer�ant dans sa cit� " +
		"lui rapporte 1 pi�ce d'or.";
	public static String ARCHITECTE=
		"L'Architecte pioche 2 cartes suppl�mentaires. Son permis " +
		"de construire est de 3 quartiers.";
	public static String ARCHIVISTE=
		"L'Archiviste pioche 7 cartes et en conserve 1 de son choix. " +
		"Son permis de construire est de 2 quartiers.";
	public static String NAVIGATRICE=
		"La Navigatrice gagne 4 pi�ce d'or ou 4 cartes. Elle ne " +
		"ne peut b�tir de quartiers.";
	public static String CAPITAINE=
		"Le Capitaine peut confisquer un quartier de co�t inf�rieur " +
		"ou �gal � 3, en payant son co�t � son propri�taire." +
		"Chaque quartier Militaire dans sa cit� lui rapporte 1 " +
		"pi�ce d'or.";
	public static String DIPLOMATE=
		"Le Diplomate peut �changer l'un de ses quartiers b�tis " +
		"avec un quartier adverse. Chaque quartier Militaire " +
		"dans sa cit� lui rapporte 1 pi�ce d'or.";
	public static String CONDOTTIERE=
		"Le Condottiere peut d�truire un quartier en payant " +
		"son co�t de construction moins 1. " +
		"Chaque quartier Militaire dans sa cit� lui rapporte 1 " +
		"pi�ce d'or.";
	public static String ARTISTE=
		"L'Artiste peut embellir un ou deux de ses quartiers en " +
		"leur assignant 1 pi�ce d'or. Un m�me quartier ne peut �tre " +
		"embelli qu'une seule fois.";
	public static String BAILLI=
		"Chaque fois qu'un joueur b�tit un quartier, il doit placer " +
		"1 pi�ce d'or sur le jeton Bailli. Prenez toutes les pi�ces" +
		"d'or sur le jeton Bailli.";
	public static String REINE=
		"Si la Reine est � c�t� du joueur qui a choisi la carte " +
		"du personnage de rang 4 (gemme jaune), elle re�oit 3 " +
		"pi�ces d'or.";
}


