import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

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
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html.  Algunes
 *   consideracions importants: indentació i espaiat consistent, bona nomenclatura de variables,
 *   declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *   declaracions). També convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for
 *   (int i = 0; ...)) sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Laura Rodríguez López
 * - Nom 2: Antoni Navarro Moreno
 * - Nom 3: Constantino Pérez Palacios
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {

   /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
   * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
   * l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si `P(x)` és
   * cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
   * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
   * petit com per poder provar tots els casos que faci falta).
   */
    static class Tema1 {

        /*
        * És cert que ∀x ∃!y. P(x) -> Q(x,y) ?
        */
        static boolean exercici1(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            int contador = 0;
            //Recorremos el universo de las x
            for (int x : universe) {
                contador = 0;
                //Recorremos el universo de las y
                for (int y : universe) {
                    //Si se cumple que P(x)->Q(x,y)
                    if (!(p.test(x)) || q.test(x, y)) {
                        //Aumentamos el contador
                        contador++;
                    }
                }
                //Si el contador es diferente de 1
                if (contador != 1) {
                    System.out.println("TEMA 1, EJERCICIO 1: FALSE");
                    return false;
                }
            }
            System.out.println("TEMA 1, EJERCICIO 1: TRUE");
            return true;
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
            //Si el contador es 1, significa que es verdadero porque solo existe una única X para toda Y
            if (contador == 1) {
                System.out.println("TEMA 1, EJERCICIO 2: TRUE");
                return true;
            }
            System.out.println("TEMA 1, EJERCICIO 2: FALSE");
            return false;
        }

        /*
        * És cert que ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
        */
        static boolean exercici3(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
            int contador = 0;
            //Recorremos el universo de las x
            for (int x : universe) {
                //Recorremos el universo de las y
                for (int y : universe) {
                    contador = 0;
                    //Recorremos el universo de las z
                    for (int z : universe) {
                        //Si se cumple P(x,z) ⊕ Q(y,z)
                        if ((p.test(x, z) && !q.test(y, z)) || (!p.test(x, z) && q.test(y, z))) {
                            //Aumentamos el contador
                            contador++;
                        }
                    }
                    //Si la condicion con la misma x se ha cumplido para todas las z
                    //la sentencia será verdadera
                    if (contador == universe.length) {
                        System.out.println("TEMA 1, EJERCICIO 3: TRUE");
                        return true;
                    }
                }
            }
            System.out.println("TEMA 1, EJERCICIO 3: FALSE");
            return false;
        }

       /*
        * És cert que (∀x. P(x)) -> (∀x. Q(x)) ?
        */
        static boolean exercici4(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            boolean Px = true;
            boolean Qx = true;
            //Recorremos el universo de las x
            for (int x : universe) {
                //Si no se cumple P(x)
                if (!(p.test(x))) {
                    Px = false;
                }
                //Si no se cumple Q(x)
                if (!(q.test(x))) {
                    Qx = false;
                }
            }
            //Comprobamos que se cumpla (∀x. P(x)) -> (∀x. Q(x))
            if (!(Px) || Qx) {
                System.out.println("TEMA 1, EJERCICIO 4: TRUE");
                return true;
            }
            System.out.println("TEMA 1, EJERCICIO 4: FALSE");
            return false;
        }

        /*
        * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
        */
        static void tests() {
            // Exercici 1
            // ∀x ∃!y. P(x) -> Q(x,y) ?

            assertThat(
                    exercici1(
                            new int[]{2, 3, 5, 6},
                            x -> x != 4,
                            (x, y) -> x == y
                    )
            );

            assertThat(
                    !exercici1(
                            new int[]{-2, -1, 0, 1, 2, 3},
                            x -> x != 0,
                            (x, y) -> x * y == 1
                    )
            );

            // Exercici 2
            // ∃!x ∀y. P(y) -> Q(x,y) ?
            assertThat(
                    exercici2(
                            new int[]{-1, 1, 2, 3, 4},
                            y -> y <= 0,
                            (x, y) -> x == -y
                    )
            );

            assertThat(
                    !exercici2(
                            new int[]{-2, -1, 1, 2, 3, 4},
                            y -> y < 0,
                            (x, y) -> x * y == 1
                    )
            );

            // Exercici 3
            // ∃x,y ∀z. P(x,z) ⊕ Q(y,z) ?
            assertThat(
                    exercici3(
                            new int[]{2, 3, 4, 5, 6, 7, 8},
                            (x, z) -> z % x == 0,
                            (y, z) -> z % y == 1
                    )
            );

            assertThat(
                    !exercici3(
                            new int[]{2, 3},
                            (x, z) -> z % x == 1,
                            (y, z) -> z % y == 1
                    )
            );

            // Exercici 4
            // (∀x. P(x)) -> (∀x. Q(x)) ?
            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3, 4, 5, 8, 9, 16},
                            x -> x % 2 == 0, // x és múltiple de 2
                            x -> x % 4 == 0 // x és múltiple de 4
                    )
            );

            assertThat(
                    !exercici4(
                            new int[]{0, 2, 4, 6, 8, 16},
                            x -> x % 2 == 0, // x és múltiple de 2
                            x -> x % 4 == 0 // x és múltiple de 4
                    )
            );
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
    * i també donarem el conjunt on està definida, per exemple
    *   int[] a = {0,1,2};
    *
    * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant el domini
    * int[] a, el codomini int[] b, i f un objecte de tipus Function<Integer, Integer> que podeu
    * avaluar com f.apply(x) (on x és d'a i el resultat f.apply(x) és de b).
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
                            break;
                        }
                    }
                }
            }

            //TRANSITIVA
            for (int i = 0; i < rel.length; i++) {
                for (int j = 0; j < rel.length; j++) {
                    for (int k = 0; k < rel.length; k++) {
                        if (rel[i][1] == rel[j][0] && rel[j][1] == rel[k][0]) {
                            // Si (indice, indice2) y (indice2, indice3) están en la relación,
                            // verificamos si (indice, indice3) también está en la relación.
                            boolean existeIK = false;
                            for (int l = 0; l < rel.length; l++) {
                                if (rel[l][0] == rel[i][0] && rel[l][1] == rel[k][1]) {
                                    existeIK = true;
                                    break;
                                }
                            }
                            if (!existeIK) {
                                transitiva = false; // No se cumple la transitividad
                            }
                        }
                    }
                }
            }

            if (reflexiva && simetrica && transitiva) {
                System.out.print("TEMA 2, EJERCICIO 1: TRUE, ");
                System.out.println("REFLEXIVA: " + reflexiva + " SIMÉTRICA: " + simetrica + " TRANSITIVA: " + transitiva);
                return true;
            }
            System.out.print("TEMA 2, EJERCICIO 1: FALSE, ");
            System.out.println("REFLEXIVA: " + reflexiva + " SIMÉTRICA: " + simetrica + " TRANSITIVA: " + transitiva);
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
                            break;
                        }
                    }
                }
            }

            //TRANSITIVA
            for (int i = 0; i < rel.length; i++) {
                for (int j = 0; j < rel.length; j++) {
                    for (int k = 0; k < rel.length; k++) {
                        if (rel[i][1] == rel[j][0] && rel[j][1] == rel[k][0]) {
                            // Si (indice, indice2) y (indice2, indice3) están en la relación,
                            // verificamos si (indice, indice3) también está en la relación.
                            boolean existeIK = false;
                            for (int l = 0; l < rel.length; l++) {
                                if (rel[l][0] == rel[i][0] && rel[l][1] == rel[k][1]) {
                                    existeIK = true;
                                    break;
                                }
                            }
                            if (!existeIK) {
                                transitiva = false; // No se cumple la transitividad
                            }
                        }
                    }
                }
            }
            if (reflexiva && simetrica && transitiva) {
                //Calculamos el cardinal del conjunto cociente de A sobre rel
                int cardinal = 0;
                //Vector para almacenar los elementos que ya hemos comprobado
                boolean[] comprobados = new boolean[a.length];

                //Recorremos los elementos del conjunto A
                for (int indice = 0; indice < a.length; indice++) {
                    //Si no hemos comprobado antes el elemento
                    if (!comprobados[indice]) {
                        //Sumamos 1 al contador
                        cardinal++;

                        //Buscamos elementos equivalentes y los marcamos como comprobados
                        for (int indice2 = 0; indice2 < rel.length; indice2++) {
                            //Verificamos si cada elemento de a tiene una
                            //correspondencia en la relación rel
                            if (a[indice] == rel[indice2][0]) {
                                for (int indice3 = 0; indice3 < a.length; indice3++) {
                                    if (a[indice3] == rel[indice2][1]) {
                                        comprobados[indice3] = true;
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("TEMA 2, EJERCICIO 2: TRUE, SOLUCIÓN: " + cardinal);
                return cardinal;
            }
            System.out.println("TEMA 2, EJERCICIO 2: FALSE");
            return -1;
        }

        /*
        * Comprovau si la relació `rel` definida entre `a` i `b` és una funció.
        *
        * Podeu soposar que `a` i `b` estan ordenats de menor a major.
        */
        static boolean exercici3(int[] a, int[] b, int[][] rel) {
            // Verificar si cada elemento tiene una imagen
            for (int elemento1 = 0; elemento1 < a.length; elemento1++) {
                boolean Imagen = false;
                for (int elemento2 = 0; elemento2 < rel.length; elemento2++) {
                    //
                    // Si los valores en rel[elemento2][0] y rel[elemento2][1] coinciden con a[elemento1] y b[elemento1] respectivamente, significa que exite una imagen
                    if ((rel[elemento2][0] == a[elemento1]) && (rel[elemento2][1] == b[elemento1])) {
                        Imagen = true;
                        break;
                    }
                }
                //Si Imagen es false significa que no es una funcion
                if (!Imagen) {
                    System.out.println("TEMA 2, EJERCICIO 3: FALSE");
                    return false;
                }
            }
            //Acaba el bucle y todos los elementos tienen una imagen por lo tanto es una funcion
            System.out.println("TEMA 2, EJERCICIO 3: TRUE");
            return true;
        }

        /*
        * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
        * - Si és exhaustiva, el màxim cardinal de l'antiimatge de cada element de `codom`.
        * - Si no, si és injectiva, el cardinal de l'imatge de `f` menys el cardinal de `codom`.
        * - En qualsevol altre cas, retornau 0.
        *
        * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major.
        */
        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            int exhaustiva = exhaustiva(dom, codom, f);
            if (exhaustiva != -1) {
                System.out.println("TEMA 2, EJERCICIO 4: EXHAUSTIVA");
                return exhaustiva;
            }
            boolean injectiva = injectiva(dom, codom, f);
            if (injectiva) {
                System.out.println("TEMA 2, EJERCICIO 4: " + (dom.length - codom.length));
                return dom.length - codom.length;
            }
            System.out.println("TEMA 2, EJERCICIO 4: 0");
            return 0;
        }

        //Comprobación exhaustividad
        static int exhaustiva(int[] dom, int[] codom, Function<Integer, Integer> f) {
            int hayAntimagen;
            int maximCardinal = 0;
            //Miramos todo el codominio
            for (int codomini : codom) {
                hayAntimagen = 0;
                //miramos todo el codominio para mirar la antimagen
                for (int domini : dom) {
                    int imagen = f.apply(dom[domini]);
                    if (imagen == codom[codomini]) {
                        hayAntimagen++;
                        break;
                    }
                }
                if (hayAntimagen == 0) {
                    return -1;
                }
                //Si no tiene antimagen no es exhaustiva
                if (maximCardinal < hayAntimagen) {
                    maximCardinal = hayAntimagen;
                }
            }
            return maximCardinal;
        }

        //Comprobación injectividad
        static boolean injectiva(int[] dom, int[] codom, Function<Integer, Integer> f) {
            int[] hayUnaImagen = new int[codom.length];
            for (int i : hayUnaImagen) {
                hayUnaImagen[i] = 0;
            }
            for (int domini : dom) {
                int imagen = f.apply(dom[domini]);
                for (int codomini : codom) {
                    if (imagen == codom[codomini]) {
                        hayUnaImagen[codomini]++;
                        if (hayUnaImagen[codomini] > 1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        /*
        * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
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
            int orden = 0;
            int medida = 0;
            int grados = 0;
            //Contamos las filas de la matriz, que se corresponden con los nodos
            for (int nodos = 0; nodos < g.length; nodos++) {
                orden++;
            }

            //Contamos el grado de los nodos
            for (int nodos = 0; nodos < g.length; nodos++) {
                for (int conexiones = 0; conexiones < g[nodos].length; conexiones++) {
                    grados++;
                }
            }
            //Calculamos, por el lema del apretón de manos, las aristas
            medida = grados / 2;

            //Calculamos, finalmente, el orden menos la medida:
            System.out.println("TEMA 3, EJERCICIO 1, SOLUCIÓN: " + (orden - medida));
            return (orden - medida);
        }

        /*
        * Suposau que el graf (no dirigit) és connex. És bipartit?
        */
        static boolean exercici2(int[][] g) {
            ArrayList<Integer> A = new ArrayList<Integer>();
            ArrayList<Integer> B = new ArrayList<Integer>();
            for (int nodo = 0; nodo < g.length; nodo++) {
                if (!B.contains(nodo) || B.isEmpty()) {
                    A.add(nodo);
                    //Comprobación de los nodos de A que van a B
                    for (int elem = 0; elem < g[nodo].length; elem++) {
                        if (!A.contains(g[nodo][elem]) && !B.contains(g[nodo][elem])) {
                            B.add(g[nodo][elem]);
                        } else if (A.contains(g[nodo][elem])) {
                            System.out.println("TEMA 3, EJERCICIO 2: FALSE");
                            return false;
                        }
                    }
                } else {
                    //Comprobación de los nodos de B que van a A
                    for (int elem = 0; elem < g[nodo].length; elem++) {
                        if (!A.contains(g[nodo][elem]) && !B.contains(g[nodo][elem])) {
                            A.add(g[nodo][elem]);
                        } else if (B.contains(g[nodo][elem])) {
                            System.out.println("TEMA 3, EJERCICIO 2: FALSE");
                            return false;
                        }
                    }
                }
            }
            System.out.println("TEMA 3, EJERCICIO 2: TRUE");
            return true;
        }

        /*
        * Suposau que el graf és un DAG. Retornau el nombre de descendents amb grau de sortida 0 del
        * vèrtex i-èssim.
        */
        static int contador_hojas_i = 0;

        static int exercici3(int[][] g, int i) {
            boolean[] visitado = new boolean[g.length];
            contador_hojas_i = 0;
            //Reiniciamos el array de nodos visitados
            for (int reinicio = 0; reinicio < g[i].length; reinicio++) {
                visitado[reinicio] = false;
            }
            //Recorremos los hijos del nodo elegido
            for (int hijos = 0; hijos < g[i].length; hijos++) {
                //Marcamos como visitado
                visitado[i] = true;
                //Y comprobamos los "nietos" del nodo
                Recursividad(g[i][hijos], g, visitado);
            }
            System.out.println("TEMA 3, EJERCICIO 3, SOLUCIÓN: " + contador_hojas_i);
            return contador_hojas_i;
        }

        static void Recursividad(int nodo, int[][] grafo, boolean[] visitado) {
            //Si no tiene hijos, significa que el nodo es una hoja
            if (grafo[nodo].length == 0) {
                visitado[nodo] = true;
                contador_hojas_i++;
            }
            //Comprobamos los hijos del nodo pasado por parámetro
            for (int nieto = 0; nieto < grafo[nodo].length; nieto++) {
                //Si no se ha visitado
                if (visitado[grafo[nodo][nieto]] == false) {
                    //Si no tiene hijos, significa que el nodo es una hoja
                    if (grafo[grafo[nodo][nieto]].length == 0) {
                        visitado[grafo[nodo][nieto]] = true;
                        contador_hojas_i++;
                    } //Si no, se vuelven a comprobar sus hijos
                    else {
                        visitado[nieto] = true;
                        Recursividad(grafo[nodo][nieto], grafo, visitado);
                    }
                }
            }
        }

        /*
        * Donat un arbre arrelat (dirigit, suposau que l'arrel es el vèrtex 0), trobau-ne el diàmetre
        * del graf subjacent. Suposau que totes les arestes tenen pes 1.
        */
        static int exercici4(int[][] g) {
            int valor = 0;
            int valorAlto = 0;
            int segundoValorAlto = 0;
            //Busqueda de las distacncias máximas de ambas salidas de la raíz
            for (int hijo = 0; hijo < g[0].length; hijo++) {

                valor = preOrden(g, g[0][hijo], 0, 0);
                if (valorAlto < valor) {

                    segundoValorAlto = valorAlto;
                    valorAlto = valor;

                } else if (segundoValorAlto < valor) {

                    segundoValorAlto = valor;

                }
            }
            System.out.println("TEMA 3, EJERCICIO 4: " + (valorAlto + segundoValorAlto));
            return (valorAlto + segundoValorAlto);
        }

        //Busqueda del nodo más lejano
        static int preOrden(int[][] arbol, int nodo, int valor, int valorMaximo) {

            valor += 1;
            if (arbol[nodo].length == 0) {
                return valor;
            }
            for (int hijo = 0; hijo < arbol[nodo].length; hijo++) {
                int aux = preOrden(arbol, arbol[nodo][hijo], valor, valorMaximo);
                if (valorMaximo < aux) {
                    valorMaximo = aux;
                }
            }
            return valorMaximo;

        }

        /*
        * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
        */
        static void tests() {
            final int[][] undirectedK6 = {
                {1, 2, 3, 4, 5},
                {0, 2, 3, 4, 5},
                {0, 1, 3, 4, 5},
                {0, 1, 2, 4, 5},
                {0, 1, 2, 3, 5},
                {0, 1, 2, 3, 4},};

            /*
               1
            4  0  2
               3
            */
            final int[][] undirectedW4 = {
                {1, 2, 3, 4},
                {0, 2, 4},
                {0, 1, 3},
                {0, 2, 4},
                {0, 1, 3},};

            // 0, 1, 2 | 3, 4
            final int[][] undirectedK23 = {
                {3, 4},
                {3, 4},
                {3, 4},
                {0, 1, 2},
                {0, 1, 2},};

            /*
                7
                0
              1   2
                3   8
                4
              5   6
            */
            final int[][] directedG1 = {
                {1, 2},     // 0
                {3},        // 1
                {3, 8},     // 2
                {4},        // 3
                {5, 6},     // 4
                {},         // 5
                {},         // 6
                {0},        // 7
                {},};


            /*
                    0
               1    2     3
                  4   5   6
                 7 8
            */
            final int[][] directedRTree1 = {
                {1, 2, 3},  // 0 = r
                {},         // 1
                {4, 5},     // 2
                {6},        // 3
                {7, 8},     // 4
                {},         // 5
                {},         // 6
                {},         // 7
                {},         // 8
            };

            /*
                0
                1
             2     3
                 4   5
                    6  7
             */
            final int[][] directedRTree2 = {
                {1},
                {2, 3},
                {},
                {4, 5},
                {},
                {6, 7},
                {},
                {},};

            assertThat(exercici1(undirectedK6) == 6 - 5 * 6 / 2);
            assertThat(exercici1(undirectedW4) == 5 - 2 * 4);

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
            int[] solucion = new int[2];
            //Aplicamos el algoritmo de Euclides extendido para obtener el mcd
            int mcd = algoritmoEuclides(a, n, solucion);

            //Comprobamos si hay solución
            if (b % mcd == 0) {
                int c = (solucion[0] * (b / mcd)) % (n / mcd);
                int m = Math.abs(n / mcd);
                if (c < 0) {
                    //Verificamos si c es negativo
                    //Si lo es, sumamos n / mcd para obtener un valor positivo
                    c = c + m;
                }
                System.out.println("TEMA 4, EJERCICIO 1, SOLUCIÓN: " + c + ", " + m);
                return new int[]{c, m};
            }

            return null;
        }

        static int algoritmoEuclides(int a, int b, int[] solucion) {
            // Si b es 0, significa que el mcd(a,b)=a
            // en la ecuación diofántica resultante (ax+by=mcd), c será 1 e y será 0
            if (b == 0) {
                solucion[0] = 1;
                solucion[1] = 0;
                return a;
            }
            //Se repite el algoritmo con los nuevos valores
            int mcd = algoritmoEuclides(b, a % b, solucion);

            int x = solucion[0];
            int y = solucion[1];
            solucion[0] = y;
            solucion[1] = x - (a / b) * y;

            return mcd;
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

            int mcd;
            int m;
            int inversoM;
            int modulo = 1;
            int resultado = 0;

            for (int i = 0; i < b.length; i++) {
                m = 1;
                modulo *= n[i];
                for (int j = 0; j < n.length; j++) {
                    if (i != j) {
                        m *= n[j];
                    }
                }
                inversoM = m%n[i];
                //Obtención máximo común divisor
                mcd = maximoComunDivisor(m, n[i]);
                if(mcd==-1 || inversoM==0){
                    System.out.println("TEMA 4, EJERCICIO 2A: NULL");
                    return null;
                }
                inversoM = bezout(n[i], inversoM)[1];
                resultado += m*inversoM*b[i];
            }
            //En caso de ser negativo lo pone en positivo
            if(resultado<0){
                resultado = (resultado%modulo)+modulo;
            }
            int solucion[] = {resultado, modulo};
            System.out.println("TEMA 4, EJERCICIO 2A: " + solucion[0] + " " + solucion[1]);
            return solucion;
        }

        static int maximoComunDivisor(int a, int b) {
            //Variable auxiliar
            int auxiliar;
            while (b != 0) {
                auxiliar = b;
                b = a % b;
                a = auxiliar;
            }
            return a;
        }

        static int[] bezout(int a, int b){
            int[][] xy = {{1,0}, {0,1}};
            int[] arrayAux = new int[xy[0].length];
            int aux;
            int cociente;

            if(a<b){
                aux = a;
                a = b;
                b = aux;
                copiar(xy[0], arrayAux);
                xy[0] = xy[1];
                copiar(arrayAux, xy[1]);
            }
            int[] arrayAux2 = new int[xy[0].length];
            while(a%b>0){
                cociente = a/b;
                aux = b;
                b = a%b;
                a = aux;
                copiar(xy[1], arrayAux2);
                xy[1][0] = xy[0][0]-(cociente*xy[1][0]);
                xy[1][1] = xy[0][1]-(cociente*xy[1][1]);
                copiar(arrayAux2, xy[0]);
            }
            return xy[1];
        }

        //Copia de contenido para no indexar
        static void copiar(int[] original, int[] copia){
            for(int i=0; i<original.length; i++){
                copia[i] = original[i];
            }
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
            int[] arrayAux;
            for(int i=0; i<a.length; i++){
                //LLamada al ejercicio 1 para resolver la congruencia
                arrayAux = exercici1(a[i], b[i], n[i]);
                b[i] = arrayAux[0];
                n[i] = arrayAux[1];
            }
            //Llamada al ejercicio 2a para resolver el problema con la estructura anterior;
            arrayAux = exercici2a(b, n);
            System.out.println("TEMA 4, EJERCICIO 2B: " + arrayAux);
            return arrayAux;
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
            //Bucle que comprueba los factores primos de un numero n, mientras n sea mayor que 1
            do {
                //si el módulo entre n y el factor primo es 0 significa que es un numero primo y lo añadimos a la lista
                //además luego dividimos n entre el factor primo para obtener el siguiente numero a descomponer
                if (n % factor_primo == 0) {
                    descomposicion.add(factor_primo);
                    n /= factor_primo;
                } //si el módulo no es 0 significa que no es primo y pasamos al siguiente factor primo
                else {
                    factor_primo++;
                }
            } while (n > 1);
            System.out.println("TEMA 4, EJERCICIO 3A, SOLUCIÓN: " + descomposicion);
            return descomposicion;
        }

        /*
        * Retornau el nombre d'elements invertibles a Z mòdul n³.
        *
        * Alerta: podeu suposar que el resultat hi cap a un int (32 bits a Java), però n³ no té perquè.
        * De fet, no doneu per suposat que pogueu tractar res més gran que el resultat.
        *
        * No podeu utilitzar `long` per solucionar aquest problema. Necessitareu l'exercici 3a.
        * No, tampoc podeu utilitzar `double`.
        */
        static int exercici3b(int n) {
            ArrayList<Integer> factorizacionN = exercici3a(n);
            int phi = 1;
            //Recorremos la factorización del número N
            while (!factorizacionN.isEmpty()) {
                int factorPrimo = factorizacionN.get(0);
                int repeticionFactorPrimo = 0;
                //Eliminamos el número almacenado en la posición actual,
                //contando cuantas veces se repite el número en la factorización
                while (!factorizacionN.isEmpty()) {
                    if (factorizacionN.get(0) == factorPrimo) {
                        //Actualizamos el contador
                        repeticionFactorPrimo++;
                        //Quitamos el factor contado del array, para poder pasar
                        //a analizar el siguiente
                        factorizacionN.remove(0);
                    } else {
                        break;
                    }
                }
                //Calculamos la fi, tal que, siendo p un número primo, entonces ϕ(p) = p^n - p^(n-1)
                phi = (int) (phi * (Math.pow(factorPrimo, repeticionFactorPrimo) - Math.pow(factorPrimo, (repeticionFactorPrimo - 1))));
            }
            //Calculamos la fi de n^3, siendo la fi de un número elevado equivalente
            //a la fi de sus factores
            phi = phi * n * n;
            System.out.println("TEMA 4, EJERCICIO 3B: " + phi);
            return phi;
        }

        /*
        * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
        */
        static void tests() {
            assertThat(Arrays.equals(exercici1(17, 1, 30), new int[]{23, 30}));
            assertThat(Arrays.equals(exercici1(-2, -4, 6), new int[]{2, 3}));
            assertThat(exercici1(2, 3, 6) == null);

            assertThat(
                    exercici2a(
                            new int[]{1, 0},
                            new int[]{2, 4}
                    )
                    == null
            );

            assertThat(
                    Arrays.equals(
                            exercici2a(
                                    new int[]{3, -1, 2},
                                    new int[]{5, 8, 9}
                            ),
                            new int[]{263, 360}
                    )
            );

            assertThat(
                    exercici2b(
                            new int[]{1, 1},
                            new int[]{1, 0},
                            new int[]{2, 4}
                    )
                    == null
            );

            assertThat(
                    Arrays.equals(
                            exercici2b(
                                    new int[]{2, -1, 5},
                                    new int[]{6, 1, 1},
                                    new int[]{10, 8, 9}
                            ),
                            new int[]{263, 360}
                    )
            );

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
    * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
    * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
    * compte, però és molt recomanable).
    *
    * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
    */
    public static void main(String[] args) {
        Tema1.tests();
        Tema2.tests();
        Tema3.tests();
        Tema4.tests();
    }

    /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
    static void assertThat(boolean b) {
      if (!b)
        throw new AssertionError();
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
