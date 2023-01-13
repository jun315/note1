
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Reader {
    void read(){
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            DAO dao = new DAO();
            System.out.print("메세지를 입력하시죠 > ");
            String Msg = bufferRead.readLine();
            System.out.println("메세지 내용 : " + Msg);

            dao.save(Msg);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    void view(){
        DTO dto = DTO.getInstance();
        int count = dto.getSequence().size();
        System.out.println("총 " + count + "개의 기록이 있다.");
        System.out.println("보고싶은 기록의 번호를 입력할것.");
        for(int i=0;i < count;i++){
            System.out.print(dto.getSequence().get(i) + "  ");
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int click = sc.nextInt();
        click = dto.getSequence().indexOf(click);
        if(click != -1){
            //click는 arraylist 위치
            System.out.println("번호 : " + dto.getSequence().get(click));
            System.out.println("내용 : " + dto.getRecord().get(click));
        }else{
            System.out.println("잘못된 번호를 입력하셨습니다.");
        }
    }

    void delete(){
        DTO dto = DTO.getInstance();
        DAO dao = new DAO();
        int count = dto.getSequence().size();
        System.out.println("삭제할 번호를 입력.");
        for(int i=0;i < count;i++){
            System.out.print(dto.getSequence().get(i) + "  ");
        }
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int click = sc.nextInt();
        click = dto.getSequence().indexOf(click);
        if(click != -1){
            System.out.println(dto.getSequence().get(click) + "삭제하는 중");
            dao.delete(click);//db인덱스 값을 넣을것.
            dto.getSequence().remove(click);
            dto.getRecord().remove(click);
        }else{
            System.out.println("잘못된 숫자 입력");
        }
    }
}
