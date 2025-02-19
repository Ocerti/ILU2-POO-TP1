package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int etalsmax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(etalsmax);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	private class Marche {
		private Etal[] etals;

		private Marche(int etalsmax) {
			etals = new Etal[etalsmax];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbproduit) {
			if (etals[indiceEtal].isEtalOccupe()) {
				System.out.println("deja occupé");
				System.exit(0);
			}
			etals[indiceEtal].occuperEtal(vendeur, produit, nbproduit);

		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtalProduit=0;
			for (int i=0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalProduit++;
				}
			}
			Etal[] etalsproduit = new Etal[nbEtalProduit];
			for (int i=0, j = 0; i< etals.length ; i++) {
				if (etals[i].contientProduit(produit)) {
					etalsproduit[j]=etals[i];
					j++;
				}
			}
			return etalsproduit;
			
			
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		private void afficherMarche() {
			int etalsnonutilisé = 0;
			for (int i = 0; i < etals.length; i++) {
				etals[i].afficherEtal();
				if (etals[i].isEtalOccupe() == false) {
					etalsnonutilisé++;
				}
			}
			if (etalsnonutilisé > 0) {
				System.out.println("Il reste " + etalsnonutilisé + " étals non utilisés dans le marché.");
			}
		}

	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}