package discretas;

import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà de 0.
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - Tendrem en compte la neteja auxiliar1 organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html.  Algunes
 *   consideracions importants: indentació auxiliar1 espaiat consistent, bona nomenclatura de variables,
 *   declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *   declaracions). També convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for
 *   (int auxiliar1 = 0; ...)) sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada auxiliar1 dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, auxiliar1 necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms auxiliar1 entregau únicament aquest fitxer.
 * - Nom 1: Laura Rodríguez López
 * - Nom 2: Antoni Navarro Moreno
 * - Nom 3: Constantino Pérez Palacios
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat auxiliar1 vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {

    /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) auxiliar1 els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si `P(x)` és
   * cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` auxiliar1
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers auxiliar1 els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per poder provar tots els casos que faci falta).
     */
    static class Tema1 {

        /*
     * És cert que ∀x ∃!y. P(x) -> Q(x,y) ?
         */
        static boolean exercici1(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            int contador = 0;
            for (int x : universe) {
                for (int y : universe) {
                    if (!(p.test(x)) || q.test(x, y)) {
                        contador++;
                    }
                }
                if (contador == 1) {
                    System.out.println("Tema 1, Ejercicio 1: TRUE");
                    return true;
                }
            }
            System.out.println("Tema 1, Ejercicio 1: FALSE");
            return false;
        }

        /*
     * És cert que ∃!x ∀y. P(y) -> Q(x,y) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {

            boolean paraTodaY = true;
            int contador = 0;
            //Comprobamos todo el universo de X
            for (int x : universe) {
                //Para la misma X comprobamos el universo de Y
                for (int y : universe) {
                    //Si la condición no se cumple, entonces la boolean pasará a ser false
                    if (!(!(p.test(y)) || (q.test(x, y)))) {
                        paraTodaY = false;
                        break;
                    }
                }
                //Si existe para toda Y se suma uno al contador
                if (paraTodaY) {
                    contador++;
                }
                paraTodaY = true;
            }
            //si el contador es 1, significa que es verdadero porque solo existe una única X para toda Y
            if (contador == 1) {
                System.out.println("Tema 1, Ejercicio 2: TRUE");
                return true;
            }
            System.out.println("Tema 1, Ejercicio 2: FALSE");
            return false;
        }

        /*
     * És cert que ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
         */
        static boolean exercici3(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
            int contador = 0;
            for (int z : universe) {
                for (int x : universe) {
                    for (int y : universe) {
                        if ((p.test(x, z) && !(q.test(y, z))) || (!(p.test(x, z)) && (q.test(y, z)))) {
                            contador++;
                        }
                    }
                }
            }
            if (contador > 0) {
                System.out.println("Tema 1, Ejercicio 3: TRUE");
                return true;
            }
            System.out.println("Tema 1, Ejercicio 3: FALSE");
            return false;
        }

        /*
     * És cert que (∀x. P(x)) -> (∀x. Q(x)) ?
         */
        static boolean exercici4(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            boolean Px = true;
            boolean Qx = true;
            for (int x : universe) {
                if (!(p.test(x))) {
                    Px = false;
                }
                if (!(q.test(x))) {
                    Qx = false;
                }
            }
            if ((!(Px)) || Qx) {
                System.out.println("Tema 1, Ejercicio 4: TRUE");
                return true;
            }
            System.out.println("Tema 1, Ejercicio 4: FALSE");
            return false;
        }

        /*
     * Aquí teniu alguns exemples auxiliar1 proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // ∀x ∃!y. P(x) -> Q(x,y) ?

//      assertThat(
//          exercici1(
//              new int[] { 2, 3, 5, 6 },
//              x -> x != 4,
//              (x, y) -> x == y
//          )
//      );
//
//      assertThat(
//          !exercici1(
//              new int[] { -2, -1, 0, 1, 2, 3 },
//              x -> x != 0,
//              (x, y) -> x * y == 1
//          )
//      );
            // Exercici 2
            // ∃!x ∀y. P(y) -> Q(x,y) ?
//      assertThat(
//          exercici2(
//              new int[] { -1, 1, 2, 3, 4 },
//              y -> y <= 0,
//              (x, y) -> x == -y
//          )
//      );
//
//      assertThat(
//          !exercici2(
//              new int[] { -2, -1, 1, 2, 3, 4 },
//              y -> y < 0,
//              (x, y) -> x * y == 1
//          )
//      );
            // Exercici 3
            // ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
//      assertThat(
//          exercici3(
//              new int[] { 2, 3, 4, 5, 6, 7, 8 },
//              (x, z) -> z % x == 0,
//              (y, z) -> z % y == 1
//          )
//      );
//
//      assertThat(
//          !exercici3(
//              new int[] { 2, 3 },
//              (x, z) -> z % x == 1,
//              (y, z) -> z % y == 1
//          )
//      );
            // Exercici 4
            // (∀x. P(x)) -> (∀x. Q(x)) ?
//      assertThat(
//          exercici4(
//              new int[] { 0, 1, 2, 3, 4, 5, 8, 9, 16 },
//              x -> x % 2 == 0, // x és múltiple de 2
//              x -> x % 4 == 0 // x és múltiple de 4
//          )
//      );
//
//      assertThat(
//          !exercici4(
//              new int[] { 0, 2, 4, 6, 8, 16 },
//              x -> x % 2 == 0, // x és múltiple de 2
//              x -> x % 4 == 0 // x és múltiple de 4
//          )
//      );
        }
    }

    /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
   * auxiliar1 també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   *
   * Les funcions f : A -> B (on A auxiliar1 B son subconjunts dels enters) les representam donant el domini
   * int[] a, el codomini int[] b, auxiliar1 f un objecte de tipus Function<Integer, Integer> que podeu
   * avaluar com f.apply(x) (on x és d'a auxiliar1 el resultat f.apply(x) és de b).
     */
    static class Tema2 {

        /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
         */
        static boolean exercici1(int[] a, int[][] rel) {
            int contador = 0;
            boolean reflexiva = false;
            boolean simetrica = true;
            boolean transitiva = true;
            boolean existeIK = false;
            //REFLEXIVIDAD
            for (int elemento1 = 0; elemento1 < a.length; elemento1++) {
                for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                    if ((a[elemento1] == rel[elemento2][0]) && (a[elemento1] == rel[elemento2][1])) {
                        contador++;
                    }
                }
            }
            if (contador == a.length) {
                reflexiva = true;
            }
            //SIMÉTRICA
            //Comprobamos si la relación es cuadrada
            if (rel.length == rel[0].length) {
                for (int elemento1 = 0; elemento1 < rel.length; elemento1++) {
                    for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                        //Si existe (indice1,indice2), también tiene que existir (indice2,indice1)
                        if (rel[elemento1][elemento2] != rel[elemento2][elemento1]) {
                            simetrica = false;
                        }
                    }
                }
            }

            //TRANSITIVA
            for (int indice1 = 0; indice1 < rel.length; indice1++) {
                for (int indice2 = 0; indice2 < rel.length; indice2++) {
                    //Comprobamos si existe (j,i) para el primer elemento de la 
                    //relación, (i,k)
                    if (rel[indice2][0] == rel[indice1][1]) {
                        //Si es así, recorremos de nuevo la relación para encontrar
                        //el elemento que confirma la transitividad en ese caso 
                        //concreto, (i,k)
                        for (int indice3 = 0; indice3 < rel.length; indice3++) {
                            //Si (i,k) existe DNF, CONTINUAR AQuí
                            if (rel[indice3][0] == rel[indice1][0] && rel[indice3][1] == rel[indice2][1]) {
                                existeIK = true;
                                break;
                            }
                        }

                        if (!existeIK) {
                            transitiva= false;
                        }
                    }
                }
            }
            System.out.println(reflexiva + " " + simetrica + " " + transitiva);
            if (reflexiva && simetrica && transitiva) {
                System.out.println("Tema 2, Ejercicio 1: TRUE");
                return true;
            }
            System.out.println("Tema 2, Ejercicio 1: FALSE");
            return false;
        }

        /*
     * Comprovau si la relació `rel` definida sobre `a` és d'equivalència. Si ho és, retornau el
     * cardinal del conjunt quocient de `a` sobre `rel`. Si no, retornau -1.
     *
     * Podeu soposar que `a` està ordenat de menor a major.
         */
        static int exercici2(int[] a, int[][] rel) {
            int contador = 0;
            boolean reflexiva = false;
            boolean simetrica = true;
            boolean transitiva = true;
            //REFLEXIVIDAD
            for (int elemento1 = 0; elemento1 < a.length; elemento1++) {
                for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                    if ((a[elemento1] == rel[elemento2][0]) && (a[elemento1] == rel[elemento2][1])) {
                        contador++;
                    }
                }
            }
            if (contador == a.length) {
                reflexiva = true;
            }

            //SIMÉTRICA
            //Comprobamos si la relación es cuadrada
            if (rel.length == rel[0].length) {
                for (int elemento1 = 0; elemento1 < rel.length; elemento1++) {
                    for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                        //Si existe (indice1,indice2), también tiene que existir (indice2,indice1)
                        if (rel[elemento1][elemento2] != rel[elemento2][elemento1]) {
                            simetrica = false;
                        }
                    }
                }
            }
            //TRANSITIVA
            for (int i = 0; i < rel.length; i++) {
                int elemento1 = rel[i][0];
                int elemento2 = rel[i][1];

                for (int j = 0; j < rel.length; j++) {
                    if (rel[j][0] == elemento2) {
                        int elemento3 = rel[j][1];

                        boolean encontrado = false;
                        for (int k = 0; k < rel.length; k++) {
                            if (rel[k][0] == elemento1 && rel[k][1] == elemento3) {
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            transitiva= false;
                        }
                    }
                }
            }

            System.out.println(reflexiva + " " + simetrica + " " + transitiva);
            if (reflexiva && simetrica && transitiva) {
                System.out.println("Tema 2, Ejercicio 1: TRUE");
                return 0;
            }
            return -1;
        }

        /*
     * Comprovau si la relació `rel` definida entre `a` auxiliar1 `b` és una funció.
     *
     * Podeu soposar que `a` auxiliar1 `b` estan ordenats de menor a major.
         */
     static boolean exercici3(int[] a, int[] b, int[][] rel) {
        // Verificar si cada elemento tiene una imagen
        for (int elemento1 = 0; elemento1 < a.length; elemento1++) {
            boolean Imagen = false;
            for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                if ((rel[elemento2][0]==a[elemento1])&&(rel[elemento2][1]==b[elemento1])) {
                    Imagen = true;
                    break;
                }
            }
            if (!Imagen) {
                System.out.println("Tema 2, Ejercicio 3: FALSE");
                return false;
            }
        }
        System.out.println("Tema 2, Ejercicio 3: TRUE");
        return true;
    }

        /*
     * Suposau que `f` és una funció amb domini `dom` auxiliar1 codomini `codom`.  Retornau:
     * - Si és exhaustiva, el màxim cardinal de l'antiimatge de cada element de `codom`.
     * - Si no, si és injectiva, el cardinal de l'imatge de `f` menys el cardinal de `codom`.
     * - En qualsevol altre cas, retornau 0.
     *
     * Podeu suposar que `dom` auxiliar1 `codom` estàn ordenats de menor a major.
         */
        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            return -1; // TO DO
        }

        /*
     * Aquí teniu alguns exemples auxiliar1 proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // `rel` és d'equivalencia?

            assertThat(
                    exercici1(
                            new int[]{0, 1, 2, 3},
                            new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 3}, {3, 1}}
                    )
            );

            assertThat(
                    !exercici1(
                            new int[]{0, 1, 2, 3},
                            new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}, {1, 2}, {1, 3}, {2, 1}, {3, 1}}
                    )
            );
            // Exercici 2
            // si `rel` és d'equivalència, quants d'elements té el seu quocient?
            final int[] int09 = {0, 1, 2, 3, 4, 5, 6, 7, 8};

            assertThat(
                    exercici2(
                            int09,
                            generateRel(int09, int09, (x, y) -> x % 3 == y % 3)
                    )
                    == 3
            );

            assertThat(
                    exercici2(
                            new int[]{1, 2, 3},
                            new int[][]{{1, 1}, {2, 2}}
                    )
                    == -1
            );

            // Exercici 3
            // `rel` és una funció?
            final int[] int05 = {0, 1, 2, 3, 4, 5};

            assertThat(
                    exercici3(
                            int05,
                            int09,
                            generateRel(int05, int09, (x, y) -> x == y)
                    )
            );

            assertThat(
                    !exercici3(
                            int05,
                            int09,
                            generateRel(int05, int09, (x, y) -> x == y / 2)
                    )
            );

            // Exercici 4
            // el major |f^-1(y)| de cada y de `codom` si f és exhaustiva
            // sino, |im f| - |codom| si és injectiva
            // sino, 0
            assertThat(
                    exercici4(
                            int09,
                            int05,
                            x -> x / 4
                    )
                    == 0
            );

            assertThat(
                    exercici4(
                            int05,
                            int09,
                            x -> x + 3
                    )
                    == int05.length - int09.length
            );

            assertThat(
                    exercici4(
                            int05,
                            int05,
                            x -> (x + 3) % 6
                    )
                    == 1
            );
        }

        /// Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
        static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
            ArrayList<int[]> rel = new ArrayList<>();

            for (int a : as) {
                for (int b : bs) {
                    if (pred.test(a, b)) {
                        rel.add(new int[]{a, b});
                    }
                }
            }

            return rel.toArray(new int[][]{});
        }
    }
/*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Donarem els grafs en forma de diccionari d'adjacència, és a dir, un graf serà un array
   * on cada element i-èssim serà un array ordenat que contendrà els índexos dels vèrtexos adjacents
   * al i-èssim vèrtex. Per exemple, el graf cicle C_3 vendria donat per
   *
   *  int[][] g = {{1,2}, {0,2}, {0,1}}  (no dirigit: v0 -> {v1, v2}, v1 -> {v0, v2}, v2 -> {v0,v1})
   *  int[][] g = {{1}, {2}, {0}}        (dirigit: v0 -> {v1}, v1 -> {v2}, v2 -> {v0})
   *
   * Podeu suposar que cap dels grafs té llaços.
   */
  static class Tema3 {
    /*
     * Retornau l'ordre menys la mida del graf (no dirigit).
     */
    static int exercici1(int[][] g) {
        int ordre = 0;
                int mida = 0;
                int graus = 0;
                //Contam les files de la matriu, que es corresponen amb els nodes
                for (int nodes = 0; nodes < g.length; nodes++) {
                    ordre++;
                }

                //Contam el grau dels nodes
                for (int nodes = 0; nodes < g.length; nodes++) {
                    for (int conexions = 0; conexions < g[nodes].length; conexions++) {
                        graus++;
                    }
                }
                //Calculam, pel lema de la encaixada de mans, la mida del graf
                mida = graus / 2;

                //Calculam, finalment, l'ordre menys la mida:
                System.out.println(ordre - mida);
                return (ordre - mida);
    }

    /*
     * Suposau que el graf (no dirigit) és connex. És bipartit?
     */
    static boolean exercici2(int[][] g) {
      return false; // TO DO
    }

    /*
     * Suposau que el graf és un DAG. Retornau el nombre de descendents amb grau de sortida 0 del
     * vèrtex i-èssim.
         */
        static int contador_hojas_i = 0;

        static int exercici3(int[][] g, int i) {
            contador_hojas_i = 0;
            for (int hijos = 0; hijos < g[i].length; hijos++) {
                Recursividad(g[i][hijos], g);
            }
            System.out.println(contador_hojas_i);
            return contador_hojas_i;
        }

        static int Recursividad(int nodo, int[][] grafo) {
            for (int nieto = 0; nieto < grafo[nodo].length; nieto++) {
                if (grafo[grafo[nodo][nieto]].length != 0) {
                    Recursividad(grafo[nodo][nieto], grafo);
                } else {
//                    System.out.println(contador_hojas_i);
                    contador_hojas_i++;
                }
            }
            System.out.println(contador_hojas_i);
            return contador_hojas_i;
        }

    /*
     * Donat un arbre arrelat (dirigit, suposau que l'arrel es el vèrtex 0), trobau-ne el diàmetre.
     * Suposau que totes les arestes tenen pes 1.
     */
    static int exercici4(int[][] g) {
      return -1; // TO DO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      final int[][] undirectedK6 = {
        { 1, 2, 3, 4, 5 },
        { 0, 2, 3, 4, 5 },
        { 0, 1, 3, 4, 5 },
        { 0, 1, 2, 4, 5 },
        { 0, 1, 2, 3, 5 },
        { 0, 1, 2, 3, 4 },
      };

      /*
         1
      4  0  2
         3
      */
      final int[][] undirectedW4 = {
        { 1, 2, 3, 4 },
        { 0, 2, 4 },
        { 0, 1, 3 },
        { 0, 2, 4 },
        { 0, 1, 3 },
      };

      // 0, 1, 2 | 3, 4
      final int[][] undirectedK23 = {
        { 3, 4 },
        { 3, 4 },
        { 3, 4 },
        { 0, 1, 2 },
        { 0, 1, 2 },
      };

      /*
             7
             0
           1   2
             3   8
             4
           5   6
      */
      final int[][] directedG1 = {
        { 1, 2 }, // 0
        { 3 },    // 1
        { 3, 8 }, // 2
        { 4 },    // 3
        { 5, 6 }, // 4
        {},       // 5
        {},       // 6
        { 0 },    // 7
        {},
      };


      /*
              0
         1    2     3
            4   5   6
           7 8
      */

      final int[][] directedRTree1 = {
        { 1, 2, 3 }, // 0 = r
        {},          // 1
        { 4, 5 },    // 2
        { 6 },       // 3
        { 7, 8 },    // 4
        {},          // 5
        {},          // 6
        {},          // 7
        {},          // 8
      };

      /*
            0
            1
         2     3
             4   5
                6  7
      */

      final int[][] directedRTree2 = {
        { 1 },
        { 2, 3 },
        {},
        { 4, 5 },
        {},
        { 6, 7 },
        {},
        {},
      };

      assertThat(exercici1(undirectedK6) == 6 - 5*6/2);
      assertThat(exercici1(undirectedW4) == 5 - 2*4);

      assertThat(exercici2(undirectedK23));
      assertThat(!exercici2(undirectedK6));

      assertThat(exercici3(directedG1, 0) == 3);
      assertThat(exercici3(directedRTree1, 2) == 3);

      assertThat(exercici4(directedRTree1) == 5);
      assertThat(exercici4(directedRTree2) == 4);
    }
  }
    
   /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * Per calcular residus podeu utilitzar l'operador %, però anau alerta amb els signes.
   * Podeu suposar que cada vegada que se menciona un mòdul, és major que 1.
     */
    static class Tema4 {

        /*
     * Donau la solució de l'equació
     *
     *   ax ≡ b (mod n),
     *
     * Els paràmetres `a` i `b` poden ser negatius (`b` pot ser zero), però podeu suposar que n > 1.
     *
     * Si la solució és x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
         */
        static int[] exercici1(int a, int b, int n) {
            return null; // TO DO
        }

        /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { x ≡ b[0] (mod n[0])
     *  { x ≡ b[1] (mod n[1])
     *  { x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada b[i] pot ser negatiu o zero, però podeu suposar que n[i] > 1. També podeu suposar
     * que els dos arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
         */
        static int[] exercici2a(int[] b, int[] n) {
            return null; // TO DO
        }

        /*
     * Donau la solució (totes) del sistema d'equacions
     *
     *  { a[0]·x ≡ b[0] (mod n[0])
     *  { a[1]·x ≡ b[1] (mod n[1])
     *  { a[2]·x ≡ b[2] (mod n[2])
     *  { ...
     *
     * Cada a[i] o b[i] pot ser negatiu (b[i] pot ser zero), però podeu suposar que n[i] > 1. També
     * podeu suposar que els tres arrays tenen la mateixa longitud.
     *
     * Si la solució és de la forma x ≡ c (mod m), retornau `new int[] { c, m }`, amb 0 ⩽ c < m.
     * Si no en té, retornau null.
         */
        static int[] exercici2b(int[] a, int[] b, int[] n) {
            return null; // TO DO
        }

        /*
     * Suposau que n > 1. Donau-ne la seva descomposició en nombres primers, ordenada de menor a
     * major, on cada primer apareix tantes vegades com el seu ordre. Per exemple,
     *
     * exercici4a(300) --> new int[] { 2, 2, 3, 5, 5 }
     *
     * No fa falta que cerqueu algorismes avançats de factorització, podeu utilitzar la força bruta
     * (el que coneixeu com el mètode manual d'anar provant).
         */
        static ArrayList<Integer> exercici3a(int n) {
            int factor_primo = 2;
            ArrayList<Integer> descomposicion = new ArrayList<>();
            boolean primer_factor = true;
            do {
                if (n % factor_primo == 0) {
                    descomposicion.add(factor_primo);
                    n /= factor_primo;
                } else {
                    factor_primo++;
                }
            } while (n > 1);
            System.out.println(descomposicion);
            return descomposicion;
        }

        /*
     * Retornau el nombre d'elements invertibles a Z mòdul n³.
     *
     * Alerta: podeu suposar que el resultat hi cap a un int (32 bits a Java), però n³ no té perquè.
     * De fet, no doneu per suposat que pogueu tractar res més gran que el resultat.
     *
     * No podeu utilitzar `long` per solucionar aquest problema. Necessitareu l'exercici 3a.
         */
        static int exercici3b(int n) {
            ArrayList<Integer> descomposicion = exercici3a(n * n * n);
            int phi = n * n * n;
            for (int factor = 0; factor < descomposicion.size(); factor++) {
                int factorValor = descomposicion.get(factor);
                phi = phi - phi / factorValor;
            }
            return phi;
        }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
//            assertThat(Arrays.equals(exercici1(17, 1, 30), new int[]{23, 30}));
//            assertThat(Arrays.equals(exercici1(-2, -4, 6), new int[]{2, 3}));
//            assertThat(exercici1(2, 3, 6) == null);
//
//            assertThat(
//                    exercici2a(
//                            new int[]{1, 0},
//                            new int[]{2, 4}
//                    )
//                    == null
//            );
//
//            assertThat(
//                    Arrays.equals(
//                            exercici2a(
//                                    new int[]{3, -1, 2},
//                                    new int[]{5, 8, 9}
//                            ),
//                            new int[]{263, 360}
//                    )
//            );
//
//            assertThat(
//                    exercici2b(
//                            new int[]{1, 1},
//                            new int[]{1, 0},
//                            new int[]{2, 4}
//                    )
//                    == null
//            );
//
//            assertThat(
//                    Arrays.equals(
//                            exercici2b(
//                                    new int[]{2, -1, 5},
//                                    new int[]{6, 1, 1},
//                                    new int[]{10, 8, 9}
//                            ),
//                            new int[]{263, 360}
//                    )
//            );

            assertThat(exercici3a(10).equals(List.of(2, 5)));
            assertThat(exercici3a(1291).equals(List.of(1291)));
            assertThat(exercici3a(1292).equals(List.of(2, 2, 17, 19)));

            assertThat(exercici3b(10) == 400);

            // Aquí 1292³ ocupa més de 32 bits amb el signe, però es pot resoldre sense calcular n³.
            assertThat(exercici3b(1292) == 961_496_064);

            // Aquest exemple té el resultat fora de rang
            //assertThat(exercici3b(1291) == 2_150_018_490);
        }
    } 

    /*
   * Aquest mètode `main` conté alguns exemples de paràmetres auxiliar1 dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia auxiliar1 també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
     */
    public static void main(String[] args) {
        Tema1.tests();
        Tema2.tests();
    }

    /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
    static void assertThat(boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
