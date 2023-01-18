// Stegreifprojekt 2: Das Labyrinth
// Team: Mari, Powder, David
// WS 2022/2023


/**
 * 
 * @author Mari, Powder, David
 * @version 1.0
 */

public class LabyrinthDurchqueren {
	
	/* KONSTANTEN */ 
	
	// KONSTANTEN UND KODIERUNG
	final static char STANDARD_CHAR = '\u0000';	// damit Standard-Chars in der Karte am Ende richtig ausgegeben werden 
	final static char SPIELER_CHAR = 'B'; 		// Char für BB-8
	final static char AUSGANG_CHAR = 'A'; 		// Char für R2-D2 bzw. den Ausgang
	final static char DUNKLE_MACHT_CHAR = 'D'; 	// Char für die Dunkle Macht: "Nein, Anakin, NEIN!!!!"
	final static int  DUNKLE_MACHT_STAERKE = 8;
	final static char LEERES_FELD_CHAR = ' ';	// Char für den Weg im Labyrinth
	final static char WAND_CHAR = '.'; 			// Char für die Wände des Labyrinths
	// KONSTANTEN FÜR DIE BLICKRICHTUNG VON BB-8
	final static char BLICK_RECHTS_CHAR = '>';
	final static char BLICK_UNTEN_CHAR = '\u2228'; // IN DER PDF: 'V'
	final static char BLICK_LINKS_CHAR = '<';
	final static char BLICK_OBEN_CHAR = '\u2227'; // IN DER PDF: '^'
	
	/*
	 * Diese Methode findet die erste Position an der ein bestimmter Char in einer Matrix genutzt wird
	 * Sie wird genutzt um die Position des Spielers und die des Ausgangs zu finden
	 */
	public static int[] charFinden(char[][] matrix, char gesuchterChar) {
		int[] position = new int[2];
		for(int i = 0; i < matrix.length; i++) {
			// i: Zeile
			for(int j = 0; j < matrix[0].length; j++) {
				// j: Spalte
				if(matrix[i][j] == gesuchterChar) {
					position[0] = i;
					position[1] = j;
					return position;
				}
			}
			System.out.println();
		}
		return null;
	}
	
	public static char blickrichtungSetzen(char[][] matrix, int[] position) {
		/*
		 * Hier wird die Richtung von BB-8 so gesetzt,  wir benutzen die Methode blickrichtungSetzen(),
		 * damit sichergestellt ist, dass beim Verwenden der MethodenistRechtsEineWand() und istVorMirEineWand()
		 * nicht auf einen Bereich außerhalb des Labyrinths zugegriffen wird
		 * Es wird ">" für die Blickrichtung nach Rechts, "<" für Links, "\u2228" für Unten und "\u2227" für eine Blickrichtung nach oben zuruück und ausgegeben
		 * 
		 */
		if(position[1] == 0) {
			return BLICK_RECHTS_CHAR;
		} else if(position[1] == matrix[0].length) {
			return BLICK_LINKS_CHAR;
		} else if(position[0] == 0) {
			return BLICK_UNTEN_CHAR;
		} else {
			return BLICK_OBEN_CHAR;
		}
	}
	/*
	 * Gibt die einzelnen Tupel einer beliebigen Matrix aus inkl. BB-8 mit aktueller Blickrichtung
	 * Die Varialbe i steht hier für die Zeile und Variable j für die Spalte
	 * 
	 */
	public static void matrixAusgeben(char[][] matrix, char richtung) {
		for(int i = 0; i < matrix.length; i++) {
			// i: Zeile
			for(int j = 0; j < matrix[0].length; j++) {
				// j: Spalte
				if(matrix[i][j] == SPIELER_CHAR) {
					System.out.print(richtung);
				} else if(matrix[i][j] == STANDARD_CHAR) {
					System.out.print(LEERES_FELD_CHAR);
				} else {
					System.out.print(matrix[i][j]);
				}
			}
			System.out.println();
		}
	}
	/*
	 * Gibt die einzelnen Tupel einer beliebigen Matrix aus ohne auf die Blickrichtung von BB-8 zu achten
	 * Auch hier steht die Varialbe i für die Zeile und die Variable  j für die Spalte
	 * 
	 */
		public static void matrixAusgeben(char[][] matrix) {
			for(int i = 0; i < matrix.length; i++) {
				// i: Zeile
				for(int j = 0; j < matrix[0].length; j++) {
					// j: Spalte
					if(matrix[i][j] == STANDARD_CHAR) {
						System.out.print(LEERES_FELD_CHAR);
					} else {
						System.out.print(matrix[i][j]);
					}
				}
				System.out.println();
			}
		}
	/*
	 * Mit dieer Methode wird überprüft, ob die Koordinaten von BB-0/des Spielers und die des Ausgangs identisch sind.
	 * Sollte dies der Fall sein so zählt das Labyrinth als durchgespielt
	 */

	public static boolean zielErreicht(int[]spielerPosition, int[]ausgangPosition) {
		if(spielerPosition[0] == ausgangPosition[0] && spielerPosition[1] == ausgangPosition[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Diese Methode dreht BB-08 um 90°
	 * Wir lassen die überprüfen in die er sich drehen soll mit den beiden Fällen (Seite == r || Seite = l)
	 * Je nachdem welcher Fall eintritt wird BB-08 num 90° nach Links oder nach Rechts gedreht
	 */
		public static char drehen(char richtung, char seite) {
		if(seite == 'r') { 
			if(richtung == BLICK_LINKS_CHAR) {
				richtung = BLICK_OBEN_CHAR;
				return richtung;
			}
			if(richtung == BLICK_UNTEN_CHAR) {
				richtung = BLICK_LINKS_CHAR;
				return richtung;
			}
			if(richtung == BLICK_RECHTS_CHAR) {
				richtung = BLICK_UNTEN_CHAR;
				return richtung;
			}
			if(richtung == BLICK_OBEN_CHAR) {
				richtung = BLICK_RECHTS_CHAR;
				return richtung;
			}
		}
		if(seite == 'l') {
			if(richtung == BLICK_LINKS_CHAR) {
				richtung = BLICK_UNTEN_CHAR; 
				return richtung;
			}
			if(richtung == BLICK_UNTEN_CHAR) {
				richtung = BLICK_RECHTS_CHAR;
				return richtung;
			}
			if(richtung == BLICK_RECHTS_CHAR) {
				richtung = BLICK_OBEN_CHAR;
				return richtung;
			} 
			if(richtung == BLICK_OBEN_CHAR) {
				richtung = BLICK_LINKS_CHAR;
				return richtung;
			}
		} 
		return richtung;
	}
	
	/*
	 * Diese Methode lässt den Spieler ein Feld in die Blickrichtung gehen
	 */
	public static int[] gehen(int[] position, char richtung) {
		if(richtung == BLICK_RECHTS_CHAR) {
			position[1] += 1;
		}
		if(richtung == BLICK_UNTEN_CHAR) {
			position[0] += 1;
		}
		if(richtung == BLICK_LINKS_CHAR) {
			position[1] -= 1;
		}
		if(richtung == BLICK_OBEN_CHAR) {
			position[0] -= 1;
		}
		return position;
	}
	
	/*
	 * Die nächsten beiden Methoden sind essentiell für die Rechte-Hand-Regel.
	 * Denn nur wenn wir die Informationen aus istVorMirEineWand() und istRechtsEineWand() haben kann der nächste
	 * Schritt des Algorithmus entschieden werden
	 * return;
	 */
	public static boolean istVorMirEineWand(char[][] matrix, int[] position, char richtung) {
		if(richtung == BLICK_RECHTS_CHAR) {
			if(matrix[position[0]][position[1] + 1] == WAND_CHAR ) {
				return true;
			}
		} 
		if(richtung == BLICK_UNTEN_CHAR) {
			if(matrix[position[0] + 1][position[1]] == WAND_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_LINKS_CHAR) {
			if(matrix[position[0]][position[1] - 1] == WAND_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_OBEN_CHAR) {
			if(matrix[position[0] - 1][position[1]] == WAND_CHAR) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean istVorMirDunkleMacht(char[][] matrix, int[] position, char richtung) {
		if(richtung == BLICK_RECHTS_CHAR) {
			if(matrix[position[0]][position[1] + 1] == DUNKLE_MACHT_CHAR ) {
				return true;
			}
		} 
		if(richtung == BLICK_UNTEN_CHAR) {
			if(matrix[position[0] + 1][position[1]] == DUNKLE_MACHT_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_LINKS_CHAR) {
			if(matrix[position[0]][position[1] - 1] == DUNKLE_MACHT_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_OBEN_CHAR) {
			if(matrix[position[0] - 1][position[1]] == DUNKLE_MACHT_CHAR) {
				return true;
			}
		}
		return false;
	}

	public static boolean istRechtsEineWand(char[][] matrix, int[] position, char richtung) {
		if(richtung == BLICK_RECHTS_CHAR) {
			if(matrix[position[0] + 1][position[1]] == WAND_CHAR ) {
				return true;
			}
		} 
		if(richtung == BLICK_UNTEN_CHAR) {
			if(matrix[position[0]][position[1] - 1] == WAND_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_LINKS_CHAR) {
			if(matrix[position[0] - 1][position[1]] == WAND_CHAR) {
				return true;
			}
		}
		if(richtung == BLICK_OBEN_CHAR) {
			if(matrix[position[0]][position[1] + 1] == WAND_CHAR) {
				return true;
			}
		}
		return false;
	}
	/*
	 * Hier lassen wir das Programm die alte Position des Spielers mit einem leeren Char überschreiten, nachdem der Spieler seinen Schritt gemacht hat
	 * Das machen wir, weil wir ja nach einem Schritt nicht mehr auf dem Feld sind
	 */
		public static char[][] matrixAktualisieren(char[][] matrix, int[] altePosition, int[] neuePosition, char richtung) {
		matrix[altePosition[0]][altePosition[1]] = LEERES_FELD_CHAR;
		matrix[neuePosition[0]][neuePosition[1]] = richtung;
		return matrix;
	}
	/*
	 * Und hier wird unsere Karte geschrieben.
	 * Nachdem wir einen Schritt gemacht haben wird unsere Position mit aktueller Blickrichtung in der Karte eingetragen
	 * Sollten wir mehrmals über ein Feld kommen, so wird die alte Blickrichtung mit der neuen Blickrichtung ersetzt
	 */
	
	public static char[][] inKarteEintragen(char[][] matrix, int[] position, char richtung) {
		matrix[position[0]][position[1]] = richtung;
		return matrix;
	}
	
	public static char[][] setzeDunkleMacht(char[][] matrix, int number) {
		int x;
		int y;
		
		for(int i = 0; i < number; i++) {
			x = (int)(Math.random()*matrix.length);
			y = (int)(Math.random()*matrix[0].length);
			
			if(matrix[x][y] != SPIELER_CHAR) {
				matrix[x][y] = DUNKLE_MACHT_CHAR;
			}
		}
		return matrix;
		
	}
	
	public static boolean aufDunklerMacht(char[][] matrix, int[] position) {
		if(matrix[position[0]][position[1]] == DUNKLE_MACHT_CHAR) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		// VARIABLEN
		char blickrichtung = BLICK_LINKS_CHAR; 		// KODIERUNG SIEHE OBEN
		int schritte = 1; 				// Je nach Größe des Labyrinths können große Datentypen benötigt werden
		char labyrinthAuswahl = 0;
		
		// Positionsvariablen
		int[] spielerPosition = new int[2];
		int[] vorherigePosition = new int[2];
		int[] ausgangPosition = new int[2];
		
		
		// BENUTZBARE LABYRINTHE
		char[][] labyrinth1 = 
				{{WAND_CHAR, WAND_CHAR, SPIELER_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, AUSGANG_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR}};
		
		char[][] labyrinth2 = 
				{{WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, AUSGANG_CHAR},
				 {WAND_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {SPIELER_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR}};
		
		char[][] labyrinth3 = 
				{{WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, AUSGANG_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR, WAND_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, LEERES_FELD_CHAR, WAND_CHAR},
				 {WAND_CHAR, SPIELER_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR, WAND_CHAR}};

		// AUSGABE DER LABYRINTH UM ENTSCHEIDUNG ZU TREFFEN
		System.out.println("Labyrinth 1:");
		matrixAusgeben(labyrinth1);
		System.out.println("Labyrinth 2:");
		matrixAusgeben(labyrinth2);
		System.out.println("Labyrinth 3:");
		matrixAusgeben(labyrinth3);
		
		
		// AUSWAHL DES LABYRINTHS
		do {
			System.out.println("Geben Sie ein, welches der drei Labyrinthe Sie auswählen möchten: (1-3)");
			labyrinthAuswahl = StaticScanner.nextChar();
		} while(labyrinthAuswahl != '1' && labyrinthAuswahl != '2' && labyrinthAuswahl != '3');
		
		char[][] ausgewaehltesLabyrinth = labyrinth1;
		
		if(labyrinthAuswahl == '1') {
			ausgewaehltesLabyrinth = labyrinth1;
		}
		if(labyrinthAuswahl == '2') {
			ausgewaehltesLabyrinth = labyrinth2;
		}
		if(labyrinthAuswahl == '3') {
			ausgewaehltesLabyrinth = labyrinth3;
		}
		
		// Initialisieren der Karte je nachdem wie groß das zu durchquerende Labyrinth ist
		char[][] karte = new char[ausgewaehltesLabyrinth.length][ausgewaehltesLabyrinth[0].length];
	
		matrixAusgeben(ausgewaehltesLabyrinth, blickrichtung);
		
		// Spieler- und Ausgangsposition ermitteln sowie Blickrichtung "ins" Labyrinth setzen
		spielerPosition = charFinden(ausgewaehltesLabyrinth, SPIELER_CHAR);
		ausgangPosition = charFinden(ausgewaehltesLabyrinth, AUSGANG_CHAR);
		blickrichtung = blickrichtungSetzen(ausgewaehltesLabyrinth, spielerPosition);
		
		matrixAusgeben(ausgewaehltesLabyrinth, blickrichtung);
		
		System.out.println("BB-08 spürt die DUNKLE SEITE der Macht...");
		
		ausgewaehltesLabyrinth = setzeDunkleMacht(ausgewaehltesLabyrinth, DUNKLE_MACHT_STAERKE);
		
		// RECHTE-HAND-METHODE UND LOGIK
		while(!zielErreicht(spielerPosition, ausgangPosition)) {
			
			System.out.println("Schritt " + schritte);
			
			// Speichern der vorherigen / noch aktueller Position
			vorherigePosition[0] = spielerPosition[0];
			vorherigePosition[1] = spielerPosition[1];
			
			// Logik des Algorithmus
			if(istRechtsEineWand(ausgewaehltesLabyrinth, spielerPosition, blickrichtung)) {
				// Wenn rechts eine Wand ist, stellt sich die Frage ob vor dem Spieler ein Wand ist
				if(istVorMirEineWand(ausgewaehltesLabyrinth, spielerPosition, blickrichtung)) {
					// Ist rechts und vor der Spieler eine Wand, so verlangt der Algorithmus eine Drehung um 90° nach links
					blickrichtung = drehen(blickrichtung, 'l');
				}
				else {
					// Ist rechts vom Spieler eine Wand, vor ihm aber nicht, so macht er einen Schritt in die Blickrichtung
					spielerPosition = gehen(spielerPosition, blickrichtung);
				}
			}
			else {
				// Wenn rechts neben dem Spieler keine Wand ist, so verlangt der Algorithmus eine Drehung um 90° nach rechts und dann einen Schritt in diese Richtung
				blickrichtung = drehen(blickrichtung, 'r');
				spielerPosition = gehen(spielerPosition, blickrichtung);
			}
			
			if(aufDunklerMacht(ausgewaehltesLabyrinth, spielerPosition)) {
				System.out.println("BB-08 was meant to avoid the dark force, not embrace it!!!");
				return;
			}
			
			// Aktualisierung des Labyrinths, Hochzählen der Schritte und eintragen des Schritts in die Karte
			schritte++;
			matrixAktualisieren(ausgewaehltesLabyrinth, vorherigePosition, spielerPosition, blickrichtung);
			inKarteEintragen(karte, spielerPosition, blickrichtung);
			
			// Ausgabe des Labyrinths nach dem Schritt
			matrixAusgeben(ausgewaehltesLabyrinth, blickrichtung);
			System.out.println();
			
			// Animation der Schritte
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		// AUSGABE AM ENDE --> Wieviele Schritte ist BB-8 gegangen und auf welchem Weg hat er den Ausgang erreicht
		System.out.println("Das Labyrinth wurde in " + schritte + " Schritten durchquert.");
		System.out.println("Das ist mein Weg:");
		matrixAusgeben(karte, blickrichtung);
	}
	
}