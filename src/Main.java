import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Reader rea = new Reader();
        DAO dao = new DAO();

        dao.get();
        int x = 1;
        while(x == 1) {

            System.out.println("할 행동 선택");
            System.out.println("1번 기록보기");
            System.out.println("2번 기록하기");
            System.out.println("3번 삭제하기");
            System.out.println("이외 숫자 입력 시 종료 글 입력시 오류남");

            int num = sc.nextInt();
            if(num == 1){
                System.out.println("1번 기록보기");
                rea.view();
            }else if(num == 2){
                System.out.println("2번 기록하기");
                rea.read();
            }else if(num == 3){ //구현 못함.
                System.out.println("3번 삭제");
                rea.delete();
            }
            else{
                System.out.println("종료");
                x = 0;
            }
        }

    }
}