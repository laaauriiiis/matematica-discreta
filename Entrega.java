package entrega;

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
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Alexandre Hierro Pedrosa
 * - Nom 2: Vicenç Jaume Obrador
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {

    /*
     * Aquí teniu els exercicis del Tema 1 (Lògica).
     *
     * Els mètodes reben de paràmetre l'univers (representat com un array) i els predicats adients
     * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un element de
     * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els predicats de dues
     * variables són de tipus `BiPredicate<Integer, Integer>` i similarment s'avaluen com
     * `p.test(x, y)`.
     *
     * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats retorneu `true`
     * o `false` segons si la proposició donada és certa (suposau que l'univers és suficientment
     * petit com per utilitzar la força bruta)
     */
    static class Tema1 {

        /*
         * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
         */
        static boolean exercici1(
                int[] universe,
                BiPredicate<Integer, Integer> p,
                Predicate<Integer> q,
                Predicate<Integer> r) {
            for (int x : universe) {
                for (int y : universe) {
                    if ((p.test(x, y)) && (!r.test(y) || !q.test(x))) {
                        return false;
                    }
                }
            }
            return true;
        }

        /*
         * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
            for (int x : universe) {
                for (int y : universe) {
                    if (!q.test(y) && p.test(x)) {
                        return false;
                    }
                }
            }
            return true;
        }

        /*
         * És cert que ¬(∃x. ∀y. y ⊆ x) ?
         *
         * Observau que els membres de l'univers són arrays, tractau-los com conjunts i podeu suposar
         * que cada un d'ells està ordenat de menor a major.
         */
        static boolean exercici3(int[][] universe) {
            int length = 0;
            int[] maxarr = null;
            boolean devolver = false;
            for (int[] x : universe) {
                if (x.length > length) {
                    length = x.length;
                    maxarr = x;
                }
            }
            for (int[] x : universe) {
                for (int j = 0; j < x.length; j++) {
                    devolver = false;
                    for (int i = 0; i < length; i++) {
                        if ((maxarr[i] == x[j]) || (x.length == 0)) {
                            devolver = true;
                            break;
                        }
                    }
                    if (devolver == false) {
                        return true;
                    }
                }
            }
            return false;
        }

        /*
         * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
         */
        static boolean exercici4(int[] universe, int n) {
            int residuo;
            for (int x : universe) {
                for (int y : universe) {
                    residuo = (x * y) % n;
                    if (residuo == 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // ∀x,y. P(x,y) -> Q(x) ^ R(y)

            assertThat(
                    exercici1(
                            new int[]{2, 3, 5, 6},
                            (x, y) -> x * y <= 4,
                            x -> x <= 3,
                            x -> x <= 3
                    )
            );

            assertThat(
                    !exercici1(
                            new int[]{-2, -1, 0, 1, 2, 3},
                            (x, y) -> x * y >= 0,
                            x -> x >= 0,
                            x -> x >= 0
                    )
            );

            // Exercici 2
            // ∃!x. ∀y. Q(y) -> P(x) ?
            assertThat(
                    exercici2(
                            new int[]{-1, 1, 2, 3, 4},
                            x -> x < 0,
                            x -> true
                    )
            );

            assertThat(
                    !exercici2(
                            new int[]{1, 2, 3, 4, 5, 6},
                            x -> x % 2 == 0, // x és múltiple de 2
                            x -> x % 4 == 0 // x és múltiple de 4
                    )
            );

            // Exercici 3
            // ¬(∃x. ∀y. y ⊆ x) ?
            assertThat(
                    exercici3(new int[][]{{1, 2}, {0, 3}, {1, 2, 3}, {}})
            );

            assertThat(
                    !exercici3(new int[][]{{1, 2}, {0, 3}, {1, 2, 3}, {}, {0, 1, 2, 3}})
            );

            // Exercici 4
            // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
            assertThat(
                    exercici4(
                            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                            11
                    )
            );

            assertThat(
                    !exercici4(
                            new int[]{0, 5, 7},
                            13
                    )
            );
        }
    }

    /*
     * Aquí teniu els exercicis del Tema 2 (Conjunts).
     * 
     * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com arrays (sense
     * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus int[][].
     *
     * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
     * només té dos elements. Per exemple
     *   int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
     * i també donarem el conjunt on està definida, per exemple
     *   int[] a = {0,1,2};
     *
     * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam donant int[] a,
     * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar com f.apply(x) (on
     * x és un enter d'a i el resultat f.apply(x) és un enter de b).
     */
    static class Tema2 {

        /*
         * És `p` una partició d'`a`?
         *
         * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`. Podeu suposar que
         * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
         */
        /**
         * Hay que buscar que todos los elementos de los conjuntos de p estan dentro
         * de a si algun elemento se repite significa que no es una particion
         * @param a
         * @param p
         * @return 
         */
        static boolean exercici1(int[] a, int[][] p) {
            int[] bool = new int[a.length];
            for (int x = 0; x < a.length; x++) {
                for (int[] y : p) {
                    for (int i = 0; i < y.length; i++) {
                        if (a[x] == y[i]) {
                            bool[x] += 1;
                        }
                    }
                }
            }
            for (int i = 0; i < bool.length; i++) {
                if (bool[i] != 1) {
                    return false;
                }
            }
            return true;
        }

        /*
         * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que `x` n'és el mínim.
         *
         * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
         */
        /**
         * Comprobar les tres propietats de la relacio parcial y si donen que es compleixen
         * comprobar que x n'es el minim si x es el minim retorna true, si no retorna false
         * @param a
         * @param rel
         * @param x
         * @return 
         */
        static boolean exercici2(int[] a, int[][] rel, int x) {
            boolean transitiva = false;
            boolean reflexiva = true;
            boolean antisimetrica = true;

            //Comprobar si es antisimetrica
            for (int i = 0; i < rel.length; i++) {
                for (int j = 1; j < rel[i].length; j++) {

                    if (rel[i][j] == rel[i][j - 1]) {
                        int[] temp = {rel[i][j], rel[i][j - 1]};

                        for (int k = 0; k < rel.length; k++) {
                            if (rel[k] == temp) {
                                antisimetrica = false;
                            }
                        }
                    }
                }
            }

            //Comprobar si es reflexiva
            boolean[] reflexivecheck = new boolean[a.length];
            int n = 0;
            for (int i = 0; i < a.length; i++) {
                int[] tempr = {a[i], a[i]};
                for (int j = 0; j < rel.length; j++) {
                    if (Arrays.equals(rel[j], tempr)) {
                        reflexivecheck[i] = true;
                    }
                }
            }
            for (boolean b : reflexivecheck) {
                if (b == false) {
                    reflexiva = false;
                }
            }
            //Comprobar si es transitiva
            for (int i = 0; i < rel.length; i++) {
                for (int j = 0; j < rel.length; j++) {
                    for (int k = 0; k < rel.length; k++) {
                        if (rel[i][1] == rel[j][0]) {
                            if (rel[k][0] != rel[k][1] && rel[k] != rel[i] && rel[k] != rel[j]) {
                                if (rel[i][0] == rel[k][0] && rel[j][1] == rel[k][1]) {
                                    transitiva = true;
                                }
                            }
                        }
                    }
                }
            }
            if (transitiva && reflexiva && antisimetrica) {
                if (a[0] == x) {
                    return true;
                }
            }
            return false;
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Trobau l'antiimatge de
         * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`). Podeu suposar
         * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats de menor a major.
         */
        static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {
            int[] temp = new int[0];
            for (int i : dom) {
                if (f.apply(i) == y) {
                    int[] newtemp = new int[temp.length + 1];
                    System.arraycopy(temp, 0, newtemp, 0, temp.length);
                    temp = newtemp;
                    temp[temp.length - 1] = i;
                }
            }
            Arrays.sort(temp);
            return temp;
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`.  Retornau:
         * - 3 si `f` és bijectiva
         * - 2 si `f` només és exhaustiva
         * - 1 si `f` només és injectiva
         * - 0 en qualsevol altre cas
         *
         * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per comoditat, podeu
         * utilitzar les constants definides a continuació:
         */
        static final int NOTHING_SPECIAL = 0;
        static final int INJECTIVE = 1;
        static final int SURJECTIVE = 2;
        static final int BIJECTIVE = INJECTIVE + SURJECTIVE;
        
        /**
         * Si dom es mas pequeño que codom puede ser injectiva o nada, si es igual
         * de tamaño puede ser bijectiva o nada, y si codom es mas pequeño que dom
         * entonces significa que es exhaustiva o nada
         * @param dom
         * @param codom
         * @param f
         * @return 
         */
        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            boolean injective;
            int tipo1 = 1;
            int[] function = new int[dom.length];
            if (dom.length < codom.length) {
                for (int i = 0; i < dom.length; i++) {
                    function[i] = f.apply(dom[i]);
                }
                int checkrepeat = 0;
                for (int i : function) {
                    injective = false;
                    for (int x : codom) {
                        if (i == x) {
                            injective = true;
                            checkrepeat++;
                            break;
                        }
                    }
                    if (injective == false) {
                        tipo1 = 0;
                        break;
                    }
                }
                if (tipo1 == 1 && checkrepeat == dom.length) {
                    return INJECTIVE;
                } else {
                    return NOTHING_SPECIAL;
                }

            } else if (dom.length > codom.length) {
                for (int i : dom) {
                    injective = true;
                    for (int x : codom) {
                        if (f.apply(i) != x) {
                            injective = false;
                            break;
                        }
                    }
                    if (injective == true) {
                        tipo1 = 0;
                        break;
                    }
                }
                if (tipo1 == 1) {
                    return SURJECTIVE;
                } else {
                    return NOTHING_SPECIAL;
                }
            } else {
                for (int i = 0; i < dom.length; i++) {
                    function[i] = f.apply(dom[i]);
                }
                Arrays.sort(function);
                if (Arrays.equals(function, codom)) {
                    return BIJECTIVE;
                } else {
                    return NOTHING_SPECIAL;
                }
            }
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // `p` és una partició d'`a`?

            assertThat(
                    exercici1(
                            new int[]{1, 2, 3, 4, 5},
                            new int[][]{{1, 2}, {3, 5}, {4}}
                    )
            );

            assertThat(
                    !exercici1(
                            new int[]{1, 2, 3, 4, 5},
                            new int[][]{{1, 2}, {5}, {1, 4}}
                    )
            );

            // Exercici 2
            // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?
            ArrayList<int[]> divisibility = new ArrayList<int[]>();

            for (int i = 1; i < 8; i++) {
                for (int j = 1; j <= i; j++) {
                    if (i % j == 0) {
                        // i és múltiple de j, és a dir, j|i
                        divisibility.add(new int[]{i, j});
                    }
                }
            }

            assertThat(
                    exercici2(
                            new int[]{1, 2, 3, 4, 5, 6, 7},
                            divisibility.toArray(new int[][]{}),
                            1
                    )
            );
            assertThat(
                    !exercici2(
                            new int[]{1, 2, 3},
                            new int[][]{{1, 1}, {2, 2}, {3, 3}, {1, 2}, {2, 3}},
                            1
                    )
            );

            assertThat(
                    !exercici2(
                            new int[]{1, 2, 3, 4, 5, 6, 7},
                            divisibility.toArray(new int[][]{}),
                            2
                    )
            );

            // Exercici 3
            // calcular l'antiimatge de `y`
            assertThat(
                    Arrays.equals(
                            new int[]{0, 2},
                            exercici3(
                                    new int[]{0, 1, 2, 3},
                                    new int[]{0, 1},
                                    x -> x % 2, // residu de dividir entre 2
                                    0
                            )
                    )
            );

            assertThat(
                    Arrays.equals(
                            new int[]{},
                            exercici3(
                                    new int[]{0, 1, 2, 3},
                                    new int[]{0, 1, 2, 3, 4},
                                    x -> x + 1,
                                    0
                            )
                    )
            );

            // Exercici 4
            // classificar la funció en res/injectiva/exhaustiva/bijectiva
            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3},
                            new int[]{0, 1, 2, 3},
                            x -> (x + 1) % 4
                    )
                    == BIJECTIVE
            );

            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3},
                            new int[]{0, 1, 2, 3, 4},
                            x -> x + 1
                    )
                    == INJECTIVE
            );

            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3},
                            new int[]{0, 1},
                            x -> x / 1
                    )
                    == SURJECTIVE
            );
            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3},
                            new int[]{0, 1, 2, 3},
                            x -> x <= 1 ? x + 1 : x - 1
                    )
                    == NOTHING_SPECIAL
            );
        }
    }

    /*
     * Aquí teniu els exercicis del Tema 3 (Aritmètica).
     *
     */
    static class Tema3 {

        /*
         * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
         * Podeu suposar que `a` i `b` són positius.
         */
        static int exercici1(int a, int b) {
            if (a == 0) {
                return b;
            }
            int guardar;
            while (b != 0) {
                guardar = b;
                b = a % b;
                a = guardar;
            }
            return a;
        }

        /*
         * Es cert que `a``x` + `b``y` = `c` té solució?.
         * Podeu suposar que `a`, `b` i `c` són positius.
         */
        /**
         * Si el residu de la divisio de mcd entre c es 0 significa que té solucio
         * @param a
         * @param b
         * @param c
         * @return 
         */
        static boolean exercici2(int a, int b, int c) {
            int guardar;
            while (b != 0) {
                guardar = b;
                b = a % b;
                a = guardar;
            }
            
            return c % a == 0;
        }

        /*
         * Quin es l'invers de `a` mòdul `n`?
         *
         * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
         */
        /**
         * Si la multiplicacio del residu de la divisio de a/n y i/n, dividit per n
         * dona 1 significa que i es l'invers si no significa que no te invers
         * @param a
         * @param n
         * @return 
         */
        static int exercici3(int a, int n) {
            for (int i = 0; i < n; i++) {
                if ((((a % n) * (i % n)) % n) == 1) {
                    return i;
                }
            }
            return -1;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // `mcd(a,b)`

            assertThat(
                    exercici1(2, 4) == 2
            );

            assertThat(
                    exercici1(1236, 984) == 12
            );

            // Exercici 2
            // `a``x` + `b``y` = `c` té solució?
            assertThat(
                    exercici2(4, 2, 2)
            );
            assertThat(
                    !exercici2(6, 2, 1)
            );
            // Exercici 3
            // invers de `a` mòdul `n`
            assertThat(exercici3(2, 5) == 3);
            assertThat(exercici3(2, 6) == -1);
        }
    }

    static class Tema4 {

        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
         */
        static int[] exercici1(int[][] A) {
            int orden = A.length;
            int mida = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (A[i][j] == 1) {
                        mida++;
                    }
                }
            }
            int[] devolver = {orden, mida / 2};
            return devolver;
        }

        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
         */
        /**
         * Si el numero de aristas dividido entre 2 es equivalente a la longitud
         * de A entonces significa que es euleriano
         * @param A
         * @return 
         */
        static boolean exercici2(int[][] A) {
            int aristas = 0;
            for (int[] A1 : A) {
                for (int j = 0; j < A1.length; j++) {
                    if (A1[j] == 1) {
                        aristas++;
                    }
                }
            }
            return aristas / 2 == A.length;
        }

        /*
         * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors,
         * retornau el nombre total de vèrtexos de l'arbre
         */
        
        /**
         * A partir de que [2E=sumatorio del grado de los nodos] podemos
         * obtener que [2E= (d+1)i (grado de los nodos interiores * la
         * cantidad de nodos interiores) + n(n al ser hijos son grado uno
         * asi que n*1=n) + d (grado de la raiz)] hay que descubrir i(nodos
         * interiores), sabemos que E es el numero de aristas que equivale a
         * [nºnodos -1] para el nº de nodos nos hacen falta los nodos
         * interiores(i), a partir de esta informacion podemos resolver un
         * sistema de ecuaciones tal que [2((n+i +1)-1)=(d+1)i + n + d];
         * este sistema queda en [2(n+i)=(d+1)i +n +d] ; [2n+2i = (d+1)i
         * +n+d] ; [2n-n-d=(d+1)i-2i] ; [n-d=di-i] ; [n-d=(d-1)i];
         * [n-d/d-1=i] A partir de aqui podemos sacar el nº de nodos que
         * equivale a [n + 1 + i]
         */
        static int exercici3(int n, int d) {
            int paso1 = n - d;
            int paso2 = d - 1;
            int i = paso1 / paso2;
            int nodos = n + i + 1;
            return nodos;
        }

        /*
         * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
         */
        /**
         * Mirar cuantos 1 hay en la matriz y luego dividirlo entre 2 si ese numero
         * resultante es de la misma longitud que la matriz entonces significa que
         * es un circulo
         * @param A
         * @return 
         */
        static boolean exercici4(int[][] A) {
            int contador = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] == 1) {
                        contador++;
                    }
                }
            }

            if (contador / 2 == A.length) {
                return true;
            } else {
                return false;
            }
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // `ordre i mida`

            assertThat(
                    Arrays.equals(exercici1(new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}}), new int[]{3, 2})
            );

            assertThat(
                    Arrays.equals(exercici1(new int[][]{{0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 0, 1}, {1, 1, 1, 0}}), new int[]{4, 5})
            );

            // Exercici 2
            // `Es eulerià?`
            assertThat(
                    exercici2(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
            );
            assertThat(
                    !exercici2(new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
            );
            // Exercici 3
            // `Quants de nodes té l'arbre?`
            assertThat(exercici3(5, 2) == 9);
            assertThat(exercici3(7, 3) == 10);

            // Exercici 4
            // `Conté algún cicle?`
            assertThat(
                    exercici4(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
            );
            assertThat(
                    !exercici4(new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
            );

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

    static void assertThat(boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :

// vim: set textwidth=100 shiftwidth=2 expandtab :
