package businessLogic;

import model.MenuItem;
import model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.TreeSet;

public interface IDeliveryServiceProcessing {
    /**
     * Creaza un produs si il adauga in lista de menu
     * pre menuItem nu este null
     * post lista de produse va contine produsul creat
     * @param menuItem
     * @param set
     * @return true daca a reusit crearea sau fals daca nu a reusit
     */
    public boolean createProduct(MenuItem menuItem, TreeSet<MenuItem> set);

    /**
     * Adauga un produs in menu
     * pre menuItem nu este null
     * post lista de produse va contine produsul creat
     * @param menuItem
     * @param set
     * @return true daca a reusit adaugarea sau fals daca nu a reusit
     */
    public boolean addProduct(MenuItem menuItem, TreeSet<MenuItem> set);

    /**
     * Sterge un produs din menu
     * pre produsul nu este null
     * post produsul nu va mai fi in lista de produse
     * @param menuItem
     * @return true daca a reusit stergerea sau fals daca nu a reusit
     */
    public boolean removeProduct(MenuItem menuItem, TreeSet<MenuItem> set);

    /**
     * Modifica un produs din menu care are titlul "title"
     * pre produsul nu este null
     * post produsul se va afla in lista de produse, dar va fi modificat
     * @param menuItem
     * @param title
     * @param initialProduct
     * @return true daca a reusit modificarea sau fals daca nu a reusit modificarea
     */
    public boolean modifyProduct(MenuItem menuItem, String title, MenuItem initialProduct, TreeSet<MenuItem> set);

    /**
     * Importa produsele din fisierul .csv primit
     * post se vor importa produsele din fisier
     * @param setWhereTheyAreInserted
     * @return true daca a reusit importarea sau fals daca nu a reusit
     */
    public boolean importProducts(TreeSet<MenuItem> setWhereTheyAreInserted);

    /**
     * Plaseaza o comanda
     * pre order-ul (comanda) nu este null
     * post comanda se va stoca in lista de comenzi
     * parm order comanda care va fi plasata
     * @param products lista de produse din comanda
     * @return true daca a reusit plasarea comenzii sau fals daca nu a reusit
     */
    public boolean placeOrder(Order order, TreeSet<MenuItem> products);

    /**
     * Gaseste produse intr-o lista de produse
     * post se vor afisa titlurile produselor gasite
     * @param stringBuilder
     * @param values
     */
    public void searchProduct(StringBuilder stringBuilder, ArrayList<String> values);

    /**
     * Genereaza report-ul despre comenzile din intervalul de timp
     * @param start
     * @param end
     */
    public void generateTimeReport(int start, int end);

    /**
     * Genereaza report-ul despre de cate ori au fost comandate produsele
     * @param orderedAmount
     */
    public void generateStockReport(int orderedAmount);

    /**
     * Genereaza report-ul despre comenzile care s-au facut intr-o anumita zi
     * @param index
     */
    public void generateDateReport(int index);

    /**
     * Genereaza report-ul despre clientii care au comandat mai mult decat decat o anumita cantitate
     * @param orderCantity
     * @param orderValue
     */
    public void generateLoyalCustomersReport(int orderCantity, int orderValue);

}
