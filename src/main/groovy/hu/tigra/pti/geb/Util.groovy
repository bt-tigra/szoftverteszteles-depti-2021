package hu.tigra.pti.geb

class Util {

    static int getRandomNumber(int start, int end) {
        Math.abs(new Random().nextInt() % (end - start)) + start
    }
}
