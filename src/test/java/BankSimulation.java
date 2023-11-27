import java.util.*;

public class BankSimulation {
    private final static Map<Integer, Customer> customers = new LinkedHashMap<>();
    //accounts (List<Account>),
    private final static List<Account> accounts = new ArrayList<>();
    //scanner (Scanner)
    private final static Scanner scannerStr = new Scanner(System.in);
    private final static Scanner scannerInt = new Scanner(System.in);

    public BankSimulation() {

    }

    public static void main(String[] args) {

        BankSimulation bankSimulation = new BankSimulation();
        bankSimulation.run();

    }

       public void run() {
        int choice;

        do {
            System.out.print("\n----- BANKA İŞLEMLERİ -----" +
                    "\n1. Müşteri Listele" +
                    "\n2. Yeni Müşteri Ekle" +
                    "\n3. Müşteri İşlemleri" +
                    "\n0. Çıkış" +
                    "\nSeçiminizi yapın: ");

            choice = scannerInt.nextInt();

            switch (choice) {

                case 1:
                    listCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    customerOperationsMenu();
                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }

        } while (choice != 0);


    public void listCustomers() {
               System.out.println("\n----- MÜŞTERİLER -----");
               for (Map.Entry<Integer, Customer> each : customers.entrySet()) {
                   System.out.println(each.getKey() + ":" + each.getValue());

               }

           }


    public void listCustomerAccounts(Customer customer) {
        System.out.println("----- " + customer.getCustomerId() +
                " " + customer.getFirstName() +
                " " + customer.getLastName() +
                " HESAPLARI -----");


        int Id=customer.getCustomerId();
       for (Account each :accounts){
           if (each.getCustomerId()==Id){
               System.out.println(each);
           }
       }


    public void addCustomer() {
        System.out.println("\n----- YENİ MÜŞTERİ EKLE -----");
        System.out.print("İsim giriniz: ");
        String isim = scannerStr.nextLine();

        System.out.print("Soyisim giriniz: ");
        String soyIsim = scannerStr.nextLine();

        System.out.print("Şehir giriniz: ");
        String sehir = scannerStr.nextLine();

        Customer customer = new Customer(isim, soyIsim, sehir);
        customers.put(customer.getCustomerId(), customer);

        System.out.println("Müşteri başarıyla eklendi. Müşteri ID: " + customer.getCustomerId());
    }

    public void customerOperationsMenu() {
        int customerId;
        do {
            System.out.println("\n----- MÜŞTERİ İŞLEMLERİ -----");
            System.out.println("0. Ana Menüye Dön");
            System.out.print("Müşteri ID girin (0 çıkış yapar): ");

            System.out.print("Müşteri ID si giriniz: ");
            customerId = scannerInt.nextInt();
            if (customerId == 0) {
                break;
            }
            int uyeVar = 0;
            for (Map.Entry<Integer, Customer> each : customers.entrySet()) {
                if (each.getKey() == customerId) {
                    uyeVar = customerId;
                    break;
                }
            }
            if (uyeVar != 0) {
                customerOperations(customers.get(customerId));
                break;
            } else {
                System.out.println("Geçersiz Müşteri ID. Tekrar deneyin.");
            }



        } while (true);
    }

    public static void customerOperationsMenu2() {
        System.out.print("1. Yeni Hesap Aç\n" +
                "2. Hesapları Listele\n" +
                "3. Para Yatır\n" +
                "4. Para Çek\n" +
                "5. Bakiye Sorgula\n" +
                "0. Ana Menüye Dön\n" +
                "Seçiminizi yapın:");
    }

    public void customerOperations(Customer customer) {
        int choice;
        do {
            System.out.println("\n----- " + customer.getFirstName() +
                    " " + customer.getLastName() +
                    " İŞLEMLERİ -----"
            );
            customerOperationsMenu2();

            choice = scannerInt.nextInt();

            switch (choice) {

                case 1:
                    openNewAccount(customer);
                    break;
                case 2:
                    listCustomerAccounts(customer);
                    break;
                case 3:
                    depositToAccount(customer);
                    break;
                case 4:
                    withdrawToAccount(customer);
                    break;
                case 5:
                    checkBalance(customer);
                    break;
                case 0:
                    System.out.println("Ana menüye dönülüyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim! Tekrar deneyin.");


            }



        } while (choice != 0);
    }

    public void openNewAccount(Customer customer) {
        int choice;
        AccountType hesapTuru;

        System.out.println("\n----- YENİ HESAP AÇ -----");
        System.out.println("1. Vadesiz Hesap");
        System.out.println("2. Tasarruf Hesabı");
        System.out.println("3. Kredi Hesabı");
        System.out.print("Hesap Türü Seçin:");

        choice=scannerInt.nextInt();
        Account yeniHesap=null;

        switch (choice){
            case 1:
                hesapTuru=AccountType.CHECKING;
                yeniHesap = new Account(customer.getCustomerId(),hesapTuru);
                break;
            case 2:
                hesapTuru=AccountType.SAVINGS;
                yeniHesap = new Account(customer.getCustomerId(),hesapTuru);
                break;
            case 3:
                hesapTuru=AccountType.CREDIT;
                yeniHesap = new Account(customer.getCustomerId(),hesapTuru);
                break;
            default:
                System.out.println("Geçersiz seçim! Yeni hesap açma işlemi iptal edildi.");
        }
        try {
            System.out.println("Hesap başarıyla açıldı. Hesap Numarası: " + yeniHesap.getAccountId());
            accounts.add(yeniHesap);
        }catch (Exception e ){
            System.out.println("Geçersiz seçim");
        }




    }

    public void depositToAccount(Customer customer) {
        System.out.println("\n----- PARA YATIR -----");
        System.out.print("Hesap Numarasını Girin: ");

        int hesapNo=scannerInt.nextInt();
        System.out.print("Tutar Giriniz :");
        int tutar=scannerInt.nextInt();
        if (tutar<=0){
            System.out.println("Geçersiz tutar işlem iptal edildi.");
        }else {
            boolean kontrol=true;
            for (Account each :accounts){
                if (each.getAccountId()==hesapNo){
                    each.setBalance(each.getBalance()+tutar);
                    kontrol=false;
                }
            }
            if (kontrol==true){
                System.out.println("Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.");
            }else
                System.out.println("Paranız Yatırıldı...");
        }

    }

    public void withdrawToAccount(Customer customer) {
        System.out.println("\n----- PARA ÇEK -----");
        System.out.print("Hesap Numarasını Girin: ");

        int hesapNo=scannerInt.nextInt();
        System.out.print("Tutar Giriniz :");
        int tutar=scannerInt.nextInt();
        if (tutar<=0){
            System.out.println("Geçersiz tutar işlem iptal edildi.");
        }else {
            boolean kontrol=true;
            for (Account each :accounts){
                if (each.getAccountId()==hesapNo){

                    if (each.getBalance()<tutar){
                        System.out.println("Yetersiz bakiye.");
                        kontrol=true;
                    }else {
                        each.setBalance(each.getBalance()-tutar);
                        kontrol=false;
                    }
                }
            }
            if (kontrol){
                System.out.println("Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.");
            }else
                System.out.println("Paranız Çekildi...");
        }


    }

    public void checkBalance(Customer customer) {
        System.out.println("\n----- BAKİYE SORGULA -----");
        System.out.print("Hesap Numarasını Girin: ");
        int accountId = scannerInt.nextInt();
        scannerInt.nextLine();

        Account account = getAccountById(accountId);
        if (account != null && account.getCustomerId() == customer.getCustomerId()) {
            System.out.println("Güncel bakiye: " + account.getBalance() + " TL");
        } else {
            System.out.println("Geçersiz Hesap Numarası veya hesap sizin değil! İşlem iptal edildi.");
        }
    }

    public Account getAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

}
