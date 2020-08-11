# # SpringBoot-Firebase

Esse projeto visa exemplificar a criação de uma API com **Spring Boot** combinado com toda a flexibilidade do **Firebase** nesse projeto decidi usar o **Gradle** para me familiarizar e conhecer melhor esse gerenciador de dependência.



## Config Firebase
> FileInputStream  serviceAccount = new  FileInputStream("testefirebase-86699.json");
FirebaseOptions  options = new  FirebaseOptions.Builder()
	.setCredentials(GoogleCredentials.fromStream(serviceAccount))
	.setDatabaseUrl("https://testefirebase-86699.firebaseio.com")
	.build();
FirebaseApp.initializeApp(options);

- **testefirebase-86699.json**: arquivo de autenticação baixado no painel do firebase.
- **DatabaseUrl**: caminho do banco firebase hospedado no GoogleCloud.
Para a configuração da conexão podemos criar uma classe com a anotação @configuration, ou implementar dentro do main, há vários caminhos, importante é conseguir inicializar o FirebaseApp.


## Controller

### [POST] /person
> public  String  save(@RequestBody  Map<String, Object> obj) {
 Firestore  db = FirestoreClient.getFirestore();
DocumentReference  docRef = db.collection("person").document();
docRef.create(obj);

Desse modo podemos quando acessamos a collection e pedimos um .document() o firebase entende que deve reservar um referencia de documento e gera um automatico assim que mandamos criar um novo documento, podemos passar um json com N propriedades que a coleção sera capaz de gravar. 


### [GET] /person

>public  Object  getPerson()  {
Firestore  db = FirestoreClient.getFirestore();
ApiFuture<QuerySnapshot> query = db.collection("person").get();
QuerySnapshot  querySnapshot = query.get();
List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
List<Object> data = new  ArrayList<Object>();
>>for (QueryDocumentSnapshot  document  :  documents) {
data.add(document.getData());
}

>return  data;

Faz uma busca simples com  *db.collection(“person”).get();*, itera todos os resultados devolvendo um json com todas as colleções da base

### [DELETE] /person
>public  Object  getPerson(@RequestParam  String  id) throws  InterruptedException, ExecutionException {
Firestore  db = FirestoreClient.getFirestore();
DocumentReference  docRef = db.collection("person").document(id);
docRef.delete();

Busca o documento na base por ID, e excluir o dados bem simples e direto. 

