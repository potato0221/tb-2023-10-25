package com.ll.domain.quotation;

import com.ll.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationController{
    private Scanner scanner;
    private List<Quotation> quotations;
    public int lastQuotationId;

    public QuotationController(Scanner scanner){
        this.scanner=scanner;
        lastQuotationId = 0;
        quotations = new ArrayList<>();

        initTestData();
    }

    private void initTestData(){
        for(int i=0;i<10;i++){
            write("명언"+i,"작가"+i);

        }
    }

    private Quotation write(String content,String authorName){
        lastQuotationId++;
        int id = lastQuotationId;

        Quotation quotation = new Quotation(id, content, authorName);
        quotations.add(quotation);
        return quotation;
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();
        Quotation quotation=write(content,authorName);

        System.out.printf("%d번 명언이 등록 되었습니다.\n", quotation.getId());
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");
        for (int i = quotations.size() - 1; i >= 0; i--) {
            Quotation quotation = quotations.get(i);
            System.out.printf("%d / %s / %s\n", quotation.getId(), quotation.getAuthor(), quotation.getSaying());

        }
    }

    public void actionRemove(Rq rq){
        int id=rq.getParamAsInt("id",0);

        if(id==0){
            System.out.println("id를 정확히 입력 해 주세요.");
            return;
        }
        int index= findQuotationIndexById(id);
        if(index==-1){
            System.out.printf("%d번 명언은 존재 하지 않습니다.\n", id);
            return;
        }
        quotations.remove(index);
        System.out.printf("%d번 명언을 삭제 했습니다.\n", id);
    }

    public void actionModify(Rq rq){
        int id=rq.getParamAsInt("id",0);

        if(id==0){
            System.out.println("id를 정확히 입력 해 주세요.");
            return;
        }
        int index= findQuotationIndexById(id);
        if(index==-1){
            System.out.printf("%d번 명언은 존재 하지 않습니다.\n", id);
            return;
        }
        Quotation quotation=quotations.get(index);

        System.out.printf("명언(기존) : %s\n",quotation.getSaying());
        System.out.print("명언 : ");
        String saying = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n",quotation.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        quotation.setSaying(saying);
        quotation.setAuthor(author);
        System.out.printf("%d번 명언이 수정 되었습니다.\n",id);

    }
    private int findQuotationIndexById(int id){
        for(int i=0;i<quotations.size();i++){
            Quotation quotation=quotations.get(i);
            if(quotation.getId()==id){
                return i;
            }
        }

        return -1;
    }


}
