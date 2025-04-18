
# Mini Framework IoC en Java

Ce projet est une implémentation simple d’un **framework d’injection de dépendances** (type Spring IoC) en Java.  
Il comprend deux versions :
- Une version basée sur **les annotations personnalisées**
- Une version basée sur un **fichier XML** (utilisant JAXB)

---

##  Fonctionnalités

- Injection via `@Autowired`  
- Détection automatique via `@Component`  
- Chargement de beans depuis `config.xml` avec JAXB  
- Exemple d’application métier (`MetierImpl` et `DaoImpl`)  
- Deux classes `Main` pour tester les deux modes (annotations et XML)

---

##  Structure du projet

```bash
mini-IoC/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── annotations/
│   │       ├── dao/
│   │       ├── metier/
│   │       ├── framework/
│   │       └── presentation/
│   └── resources/
│       └── config.xml
├── README.md
```

---

##  Comment exécuter

###  Version Annotations

```java
MyInjector injector = new MyInjector();
injector.loadFromAnnotations(MetierImpl.class, DaoImpl.class);
MetierImpl metier = (MetierImpl) injector.getBean("MetierImpl");
System.out.println("Résultat = " + metier.calcul());
```

###  Version XML

#### Fichier `config.xml` :
```xml
<beans>
    <bean id="dao" class="dao.DaoImpl"/>
    <bean id="metier" class="metier.MetierImpl">
        <property name="dao" ref="dao"/>
    </bean>
</beans>
```

#### Code Java :
```java
Object metierBean = MyInjector.loadFromXML("config.xml");
MetierImpl metier = (MetierImpl) metierBean;
System.out.println("Résultat XML = " + metier.calcul());
```

---

##  Technologies utilisées

- Java 8
- IntelliJ IDEA
- JAXB (Java Architecture for XML Binding)

---

##  Projet académique

> Ce mini-framework a été développé dans le cadre d’un projet pédagogique illustrant le principe d’**Inversion de Contrôle (IoC)** et l’**Injection de Dépendances**, dans une optique de simplification et de compréhension des concepts derrière Spring.

---

##  Auteur

- **Nom** : Souidi Naoual  
- **Email** : [nawalsouidi.445@gmail.com](mailto:nawalsouidi.445@gmail.com)  
- **Université** : *Université Moulay Ismaïl-Meknès*

---

```

