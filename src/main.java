
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class main {

    public static void imprimirArray(ArrayList<Punto> P) {
        for (int i = 0; i < P.size(); i++) {
            System.out.println("x: " + P.get(i).x + " y: " + P.get(i).y);
        }
        System.out.println("-------------");
    }

    public static ArrayList<Punto> ordenarPorX(ArrayList<Punto> P) {
        ArrayList<Punto> Paux = new ArrayList<>();
        Punto[] Puntos = new Punto[P.size()];
        for (int i = 0; i < P.size(); i++) {
            Puntos[i] = P.get(i);
        }
        Punto aux;
        int i, j;
        for (i = 0; i < Puntos.length - 1; i++) {
            for (j = 0; j < Puntos.length - i - 1; j++) {
                if (Puntos[j + 1].x < Puntos[j].x) {
                    aux = Puntos[j + 1];
                    Puntos[j + 1] = Puntos[j];
                    Puntos[j] = aux;
                }
            }
        }
        Paux.addAll(Arrays.asList(Puntos));
        return Paux;
    }

    public static ArrayList<Punto> ordenarPorY(ArrayList<Punto> P) {
        ArrayList<Punto> Paux = new ArrayList<>();
        Punto[] Puntos = new Punto[P.size()];
        for (int i = 0; i < P.size(); i++) {
            Puntos[i] = P.get(i);
        }
        Punto aux;
        int i, j;
        for (i = 0; i < Puntos.length - 1; i++) {
            for (j = 0; j < Puntos.length - i - 1; j++) {
                if (Puntos[j + 1].y < Puntos[j].y) {
                    aux = Puntos[j + 1];
                    Puntos[j + 1] = Puntos[j];
                    Puntos[j] = aux;
                }
            }
        }
        Paux.addAll(Arrays.asList(Puntos));
        return Paux;
    }

    public static double obtenerDistancia(Punto A, Punto B) {
        return Math.sqrt(Math.pow((A.x - B.x), 2) + Math.pow((A.y - B.y), 2));
    }

    public static double fuerzaBruta(ArrayList<Punto> P) {
        double Minimo = Double.POSITIVE_INFINITY;
        for (int i = 0; i < P.size(); i++) {
            for (int j = i + 1; j < P.size(); j++) {
                double distancia = obtenerDistancia(P.get(i), P.get(j));
                if (distancia < Minimo) {
                    Minimo = distancia;
                }
            }
        }
        return Minimo;
    }

    public static double recursion(ArrayList<Punto> Px, ArrayList<Punto> Py) {

        int tamaño = Px.size();
        if (tamaño <= 3) {
            return fuerzaBruta(Px);
        }
        int mitad = tamaño / 2;
        ArrayList<Punto> Pl = new ArrayList<>(Px.subList(0, mitad));
        ArrayList<Punto> Pr = new ArrayList<>(Px.subList(mitad, tamaño));
        double dl = recursion(Pl, ordenarPorY(Pl));
        double dr = recursion(Pr, ordenarPorY(Pr));
        double d = Math.min(dl, dr);

        double PuntoMedio = Pr.get(0).x;
        ArrayList<Punto> LineaMedio = new ArrayList<>();
        for (int i = 0; i < Py.size(); i++) {
            if (Math.abs((Py.get(i).x - PuntoMedio)) < d) {
                LineaMedio.add(Py.get(i));
            }
        }
        return Math.min(d, obtenerDistanciaCercanaLineaMedio(LineaMedio, d));
    }

    public static double obtenerDistanciaCercanaLineaMedio(ArrayList<Punto> LineaMedio, double distancia) {
        double distMinima = distancia;
        int tamaño = LineaMedio.size();
        for (int i = 0; i < tamaño; i++) {
            for (int j = i + 1; j < tamaño; j++) {
                if ((LineaMedio.get(j).y - LineaMedio.get(i).y) >= distMinima) {
                    break;
                }
                double dist = obtenerDistancia(LineaMedio.get(i), LineaMedio.get(j));
                if (dist < distMinima) {
                    distMinima = dist;
                }
            }
        }
        return distMinima;
    }

    public static void main(String[] args) {

        ArrayList<Punto> Puntos = new ArrayList<>();
        Puntos.add(new Punto(-1, 1));
        Puntos.add(new Punto(-1, -2));
        Puntos.add(new Punto(0, 4));
        Puntos.add(new Punto(-4, -1));
        Puntos.add(new Punto(-3, 3));
        Puntos.add(new Punto(3, 3));
        Puntos.add(new Punto(3, -1));
        Puntos.add(new Punto(4, -4));
        Puntos.add(new Punto(-2, 0));
        Puntos.add(new Punto(-2, -3));
        Puntos.add(new Punto(1, 3));
        Puntos.add(new Punto(1, -2));
        Puntos.add(new Punto(2, 1));
        Puntos.add(new Punto(2, 0));
        Puntos.add(new Punto(2, -2));
        
        Double DistanciaMasCorta = recursion(ordenarPorX(Puntos), ordenarPorY(Puntos));
        System.out.println("Distancia más corta: " + DistanciaMasCorta);
        
        for (int i = 0; i < Puntos.size(); i++) {
            for (int j = i + 1; j < Puntos.size(); j++) {
                double distancia = obtenerDistancia(Puntos.get(i), Puntos.get(j));
                if (distancia == DistanciaMasCorta ) {
                    System.out.println("("+Puntos.get(i).x + ", " + Puntos.get(i).y + ") --> (" + Puntos.get(j).x + ", " + Puntos.get(j).y+")");
                }
            }
        }
        
    }

}
