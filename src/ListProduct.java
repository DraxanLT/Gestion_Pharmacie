import java.util.Random;

public class ListProduct {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy("PharmaPlus", "123 Rue de la Santé, Paris");

        CategoryProduct medoc1 = new CategoryProduct("Médicaments", "Antibiotique");
        Product amo = new Medecine(1,"Amoxicilline",5.99, new Random().nextInt(10),"Antibiotique à large spectre pour les infections bactériennes.");
        Product amo2 = new Medecine(2,"Azithromycine", 7.49, new Random().nextInt(10),"Traitement des infections respiratoires et ORL.");
        Product amo3 = new Medecine(3,"Ciprofloxacine",8.29,new Random().nextInt(10),"Antibiotique utilisé pour les infections urinaires.");
        medoc1.addProduct(amo);
        medoc1.addProduct(amo2);
        medoc1.addProduct(amo3);


        CategoryProduct medoc2 = new CategoryProduct("Médicaments", "Antalgique");
        Product amo4 = new Medecine(4,"Paracétamol",1.99,new Random().nextInt(10),"Traitement de la douleur et de la fièvre.");
        Product amo5 = new Medecine(5,"Ibuprofène", 3.49,new Random().nextInt(10),"Anti-inflammatoire non stéroïdien.");
        Product amo6 = new Medecine( 6,"Tramadol", 9.99, new Random().nextInt(10),"Antalgique pour les douleurs modérées à sévères.");
        medoc2.addProduct(amo4);
        medoc2.addProduct(amo5);
        medoc2.addProduct(amo6);


        CategoryProduct cosme = new CategoryProduct("Cosmétique", "Soins de la peau");
        Product amo7 = new Cosmetic(7, "Crème hydratante",14.99,new Random().nextInt(10),"Crème pour hydrater les peaux sèches.");
        Product amo8 = new Cosmetic(8,"Gel nettoyant visage",9.49, new Random().nextInt(10),"Gel doux pour nettoyer le visage en profondeur.");
        Product amo9 = new Cosmetic(9,"Sérum anti-âge", 24.99,new Random().nextInt(10), "Sérum pour réduire les signes de vieillissement.");
        cosme.addProduct(amo7);
        cosme.addProduct(amo8);
        cosme.addProduct(amo9);


        CategoryProduct para = new CategoryProduct("Parapharmacie", "Hygiene bucco-dentaire");
        Product amo10 = new Parapharmacy(10,"Brosse à dents électrique",39.99,new Random().nextInt(10),"Brosse à dents électrique avec plusieurs modes.");
        Product amo11 = new Parapharmacy(11,"Dentifrice blancheur", 3.99,new Random().nextInt(10),"Dentifrice pour des dents plus blanches.");
        Product amo12 = new Parapharmacy(12,"Bain de bouche antiseptique", 6.49,new Random().nextInt(10),"Solution pour une hygiène buccale complète.");
        para.addProduct(amo10);
        para.addProduct(amo11);
        para.addProduct(amo12);


        CategoryProduct comp = new CategoryProduct("Compléments alimentaire", "Vitamine");
        Product amo13 = new ComplementaryFood(13, "Vitamine C",12.99,new Random().nextInt(10),"Booste le système immunitaire et réduit la fatigue.");
        Product amo14 = new ComplementaryFood(14,"Vitamine D", 8.49,new Random().nextInt(10),"Améliore la santé des os et du système immunitaire.");
        Product amo15 = new ComplementaryFood(15, "Oméga-3", 19.99,new Random().nextInt(10),"Améliore la santé cardiovasculaire.");
        comp.addProduct(amo13);
        comp.addProduct(amo14);
        comp.addProduct(amo15);

        pharmacy.addProduct(comp);
        pharmacy.addProduct(para);
        pharmacy.addProduct(cosme);
        pharmacy.addProduct(medoc1);
        pharmacy.addProduct(medoc2);

        SearchProduct.searchProductByName();


        pharmacy.LowStock();

    }
}
