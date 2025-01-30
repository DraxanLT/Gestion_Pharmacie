import java.util.Random;
import java.util.Scanner;

public class ListProduct {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy("PharmaPlus", "123 Rue de la Santé, Paris");

        CategoryProduct medoc1 = new CategoryProduct("Médicaments");
        CategoryProduct medoc2 = new CategoryProduct("Antibiotique");
        Product amo = new Product(1,"Amoxicilline",5.99, new Random().nextInt(300),"Antibiotique à large spectre pour les infections bactériennes.", medoc1, medoc2);
        Product amo2 = new Product(2,"Azithromycine", 7.49, new Random().nextInt(300),"Traitement des infections respiratoires et ORL.", medoc1, medoc2);
        Product amo3 = new Product(3,"Ciprofloxacine",8.29,new Random().nextInt(300),"Antibiotique utilisé pour les infections urinaires.", medoc1, medoc2);
        pharmacy.addProduct(amo);
        pharmacy.addProduct(amo2);
        pharmacy.addProduct(amo3);


        CategoryProduct medoc3 = new CategoryProduct("Antalgique");
        Product amo4 = new Product(4,"Paracétamol",1.99,new Random().nextInt(300),"Traitement de la douleur et de la fièvre.", medoc1, medoc3);
        Product amo5 = new Product(5,"Ibuprofène", 3.49,new Random().nextInt(300),"Anti-inflammatoire non stéroïdien.", medoc1, medoc3);
        Product amo6 = new Product( 6,"Tramadol", 9.99, new Random().nextInt(300),"Antalgique pour les douleurs modérées à sévères.", medoc1, medoc3);
        pharmacy.addProduct(amo4);
        pharmacy.addProduct(amo5);
        pharmacy.addProduct(amo6);


        CategoryProduct medoc4 = new CategoryProduct("Cosmétique");
        CategoryProduct medoc5 = new CategoryProduct("Soins de la peau");
        Product amo7 = new Product(7, "Crème hydratante",14.99,new Random().nextInt(300),"Crème pour hydrater les peaux sèches.", medoc4, medoc5);
        Product amo8 = new Product(8,"Gel nettoyant visage",9.49, new Random().nextInt(300),"Gel doux pour nettoyer le visage en profondeur.", medoc4, medoc5);
        Product amo9 = new Product(9,"Sérum anti-âge", 24.99,new Random().nextInt(300), "Sérum pour réduire les signes de vieillissement.", medoc4, medoc5);
        pharmacy.addProduct(amo7);
        pharmacy.addProduct(amo8);
        pharmacy.addProduct(amo9);


        CategoryProduct para = new CategoryProduct("Parapharmacie");
        CategoryProduct para1 = new CategoryProduct("Hygiene bucco-dentaire");
        Product amo10 = new Product(10,"Brosse à dents électrique",39.99,new Random().nextInt(300),"Brosse à dents électrique avec plusieurs modes.", para, para1);
        Product amo11 = new Product(11,"Dentifrice blancheur", 3.99,new Random().nextInt(300),"Dentifrice pour des dents plus blanches.", para, para1);
        Product amo12 = new Product(12,"Bain de bouche antiseptique", 6.49,new Random().nextInt(300),"Solution pour une hygiène buccale complète.", para, para1);
        pharmacy.addProduct(amo10);
        pharmacy.addProduct(amo11);
        pharmacy.addProduct(amo12);


        CategoryProduct comp = new CategoryProduct("Compléments alimentaire");
        CategoryProduct comp1 = new CategoryProduct("Vitamine");
        Product amo13 = new Product(13, "Vitamine C",12.99,new Random().nextInt(300),"Booste le système immunitaire et réduit la fatigue.", comp, comp1);
        Product amo14 = new Product(14,"Vitamine D", 8.49,new Random().nextInt(300),"Améliore la santé des os et du système immunitaire.", comp, comp1);
        Product amo15 = new Product(15, "Oméga-3", 19.99,new Random().nextInt(300),"Améliore la santé cardiovasculaire.", comp, comp1);
        pharmacy.addProduct(amo13);
        pharmacy.addProduct(amo14);
        pharmacy.addProduct(amo15);


        OrderManager.orderMenu();



        /*Product findProduct = SearchProduct.searchProductByName(pharmacy, "vitamine c");
        Scanner sc = new Scanner(System.in);
        int nombre = 0;
        System.out.print("Entrez lgdgdsfd: ");
        nombre = sc.nextInt();



        System.out.println("il faut : "+ nombre+ " "+  findProduct.getName());

        pharmacy.showlowstock();*/

    }
}
