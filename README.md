# Santa Claus is coming to ACS students

##### Corina-Angi Bălănescu
##### 321CD

## Etapa 1

### Flow
În clasa *main* (pachetul *main*) începe logica programului. 
- Se creează fișierele de output 
- Se inițializează datele de intrare
- Se apelează metodele clasei Simulation (pachetul main)
- Se scrie rezultatul în fișierul de output.

Prelucrarea datelor de intrare și ieșire se realizează în cadrul pachetului *fileio*.

##### Clasa Simulation
Metodele acestei clase reprezintă rezolvarea efectivă a temei.
- Metoda *roundZero* - Runda zero necesită mai puține operații asupra entităților, 
de aceea am ales să o separ.

- Metoda *round* - Aceasta este apelată în main pentru fiecare *annualChange*.

În cadrul metodelor, se inițializează vizitatorii corespunzători rundei și sunt apelați 
într-o ordine specifică (un vizitator depinde de rezultatul celui dinaintea sa).

Pentru o viitoare extindere (*etapa 2*), doar voi adăuga noi vizitatori în listă.

### Design Patterns

##### Visitor
În cadrul pachetului *visitors* se află entitățile necesare implementării acestui design
pattern și implementări concrete ale vizitatorilor.
Clasele care implementează interfața Visitable sunt Santa (pachetul *santa*) și Child
(pachetul *child*).

Fiecare vizitator reprezintă implementarea operațiilor necesare pentru rezolvarea
temei (numele fiecărei clase denotă operația).

De ce l-am folosit:
- Sunt puține clase de modelat (doar 2 - Child și Santa)
- Pot adăuga noi operații mai rapid (nu este nevoie să modific clasa originală)
- Fiind dependente una de cealaltă (trebuie metode corespunzătoare în ambele clase), este mai 
ușor să implementez noi operații în cele două deodată

##### Observer
În cadrul pachetului *observers* se află entitățile necesare implementării acestui design
pattern și observatorul Output. Clasa care moștenește Observable este Santa (clasa care este 
observată).
Când este notificat de o schimbare, observatorul Output adaugă în fișier lista de copii a 
clasei Santa (fapt care se întâmplă după fiecare actualizare adusă de annualChange).

De ce l-am folosit:
- Nu interacționează propriu-zis cu clasa Santa, ci doar observă actualizările și le scrie
  în fișierul de output (apelează metoda care le scrie)

## Etapa 2

Noile acțiuni au fost implementate în clase de tip vizitator (numele clasei denotă operația).

### Design Patterns

##### Strategy
În cadrul pachetului *strategies* se află entitățile necesare implementării acestui design
pattern și implementări concrete ale strategiilor. Fiecare strategie reprezintă o metodă de
ordonare a copiilor pe baza unor diferite criterii (numele clasei reprezintă criteriul).

De ce l-am folosit:
- Operația de sortare a copiilor avea nevoie de o implementare diferită la fiecare rundă,
dar la bază constituia aceeași acțiune - o reordonare. Prin urmare, am împărțit algoritmii
necesari în clase de tip strategie
- Algoritmul de sortare este implementat separat de logica operației în contextul
căreia este folosit (este apelat în cadrul ReceivedGifts pentru a se oferi cadourile 
în ordinea cerută)

##### Factory
Am implementat design pattern-ul Factory pentru a crea tipul de strategie menționat în
cadrul input-ului.

De ce l-am folosit:
- Instanțierea clasei nu este relevantă în ReceivedGifts care utilizează doar metoda 
suprascrisă în implementările concrete ale strategiilor
- Strategia care trebuie creată depinde de un string primit la fiecare rundă. Factory 
îmi permite să comut la **runtime** între strategiile care trebuie aplicate