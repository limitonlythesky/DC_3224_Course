package Task1;

public class Compute {
    String ans = "";
    public Compute() {
    }

    private void rec(int n, int i, int j, int k){
        if (n == 1){
            if (i == 1 && j == 3 || i == 2 && j == 1 || i == 3 && j == 2){
                ans += n + " - " + i + " -> " + k + "\n";
                ans += n + " - " + k + " -> " + j + "\n";
                return;
            }
            else
                ans += n + " - " + i + " -> " + j + "\n";
        }
        else{
            if (i == 1 && j == 3 || i == 2 && j == 1 || i == 3 && j == 2){
                rec(n, i, k, j);
                rec(n, k, j, i);
            }else{
                rec(n - 1, i, k, j);
                ans += n + " - " + i + " -> " + j + "\n";
                rec(n - 1, k, j, i);
            }
        }
    }

    public String solution(int num){
        rec(num, 1, 3, 2);
        return ans;
    }

}
