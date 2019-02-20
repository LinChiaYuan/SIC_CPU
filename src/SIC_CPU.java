/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.*;

public class SIC_CPU
{

    public static void main(String[] args)
    {
        /*            讀 SIC.txt                                   */
        Scanner Keyboard = new Scanner(System.in);
        Scanner filenIn = null;
        PrintWriter file_List_Out = null;
        PrintWriter file_Ob_Out = null;
        String file[]= new String[10000];
        int i= 0,total=0,count=0;
        SIC_Instruct comment_SIC_Instruct = new SIC_Instruct();
        boolean jump_comment=false;

        try     //   讀取test_1
        {
            filenIn = new Scanner (new FileInputStream("Assembler_Sic_2.txt"));

            while(filenIn.hasNext())    //把文件變成string陣列,一個一個字串讀
            {
                String comment= filenIn.next();

                if(comment.charAt(0) == '.')         //判斷 .121321 之註解
                    comment=filenIn.nextLine();
                else if (comment.equals("START"))      //判斷 START
                {
                    file[i] = comment;
                    i++;
                    jump_comment=true;
                }
                else if(comment_SIC_Instruct.instruct_equal(comment)) //判斷 是否為指令碼
                {
                    if(comment.equals("RSUB"))             //判斷 RSUB 後直接清除一行
                    {
                        file[i] = comment;
                        i++;
                        comment=filenIn.nextLine();
                    }
                    else if(comment.equals("END"))     //判斷 END 後不清一行(不知道為啥讀不到)
                    {
                        file[i] = comment;
                        i++;
                    }
                    else                                //指令碼後 除特殊指另外 ,清一行旗標為1
                    {
                        file[i] = comment;
                        i++;
                        jump_comment=true;

                    }
                }
                else                                   //指令碼後 op輸入後 ,清一行,清一行旗標為0
                {
                    file[i] = comment;
                    i++;
                    if(jump_comment)
                    {
                        comment=filenIn.nextLine();
                        jump_comment=false;
                    }
                }
            }
            total=i;                               //陣列總長
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error test_1");
            System.exit(0);
        }

        try    //   輸出ListFile
        {
            file_List_Out = new PrintWriter (new FileOutputStream("ListFile.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error test_2");
            System.exit(0);
        }

        try    //   輸出ObjectFile
        {
            file_Ob_Out = new PrintWriter (new FileOutputStream("ObjectFile.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error test_2");
            System.exit(0);
        }

        /*            用於判斷是否為指令碼 class                   */
        SIC_Instruct Judge = new SIC_Instruct();

        /*            主程式陣列 class                             */
        SIC_mainProgram[] Main_code = new SIC_mainProgram[100];
        for(int j=0;j<100;j++)                                     //智能物件
            Main_code[j]= new SIC_mainProgram();
        int num=0,result_total=0;

        /*          輸入第一行 Name       						   */
        String start_name =file[count] ;  count++;
        String start_instruct =file[count] ;  count++;
        String start_add = file[count] ;  count++;
        /*                 開始那行               					       */
        SIC_name Start_code = new SIC_name(start_name,start_instruct,start_add);

        /*           輸入主程式            */
        String ma_name , ma_instruct , ma_add , ma_use ;     //輸入主程式的變數

        do
        {
            ma_name= file[count] ;  count++;
            if( Judge.instruct_equal(ma_name))         //判斷是否為指令碼
            {
                ma_instruct=ma_name;
                Main_code[num].setMain_instruct(ma_instruct);
                if(ma_instruct.equals("RSUB"))              //為RSUB ,use設空白
                {
                    Main_code[num].setMain_use("      ");
                }
                else                                           //放入op
                {
                    ma_use= file[count] ;
                    count++;
                    Main_code[num].setMain_use(ma_use);
                }
            }
            else                                              //不為指令就是標籤
            {
                Main_code[num].setMain_name(ma_name);			//放入標籤
                ma_instruct= file[count] ;  count++;            //放入指令碼
                Main_code[num].setMain_instruct(ma_instruct);
                if(ma_instruct.equals("RSUB"))              //為RSUB ,use設空白
                {
                    Main_code[num].setMain_use("      ");
                }
                else										//放入op
                {
                    ma_use= file[count] ;
                    count++;
                    Main_code[num].setMain_use(ma_use);
                }
            }

            num++;                                  //num計算主程式陣列長度
            if(total<=count)                      //等於會超出string暫存陣列長度 break
                break;
        }
        while(true);
        result_total=num;                           //暫存主程式陣列長度


        /*               增加位置            */
        SIC_counter Counter = new SIC_counter();
        int temp=0;
        num=0;

        Main_code[num].setMain_address(Start_code.getAddress());     //把起始位置放入主程式第一位置
        do
        {                                            //輸入 位置&指令碼&op  回傳 10進位位置
            temp=Counter.address_add(Main_code[num].getMain_address(), Main_code[num].getMain_instruct(), Main_code[num].getMain_use());
            num++;
            Main_code[num].setMain_address(Integer.toString(temp));   //把回傳數字放入下個的位置
        }while(num!=result_total);          //值到超出陣列 離開

        /*               增加 ob-code            */
        SIC_counter_ob_code Counter_ob_code = new SIC_counter_ob_code ();
        String tamp="0";
        num=0;
        do
        {
            String sreach = Main_code[num].getMain_use();
            for(int k=0;k<result_total;k++)             //尋找標籤的位置
            {

                if(k==num)
                    k++;                                      //判斷 ,X

                if(sreach.equals(Main_code[k].getMain_name()))
                {
                    tamp=  Main_code[k].getMain_address();         //暫存標籤位置
                    break;
                }

                if(sreach.indexOf(",X") >0 )     //  處理,X  (要加X)
                {
                    int str=sreach.indexOf(",X");
                    if(sreach.substring(0, str).equals(Main_code[k].getMain_name()))
                    {
                        tamp=  Main_code[k].getMain_address();         //暫存標籤位置
                        break;
                    }
                }



            }                                      //計算ob - code  後存入
            Main_code[num].setMain_obcode(Counter_ob_code.ob_code_add(tamp, Main_code[num].getMain_instruct(), Main_code[num].getMain_use()));
            num++;
        }while(num!=result_total);

        /*               結果輸出到   ListFile.txt       */
        file_List_Out.println(Start_code);
        for(int k=0;k<result_total;k++)
        {
            file_List_Out.println(Main_code[k]);
        }

        /*               結果輸出到   ObjectFile.txt       */
        SIC_ObjectFile SIC_Ob_File = new SIC_ObjectFile();

        //      H_Card 輸入
        file_Ob_Out.println(SIC_Ob_File.H_Ccard(Start_code.getName(), Start_code.getAddress(), Main_code[result_total-1].getMain_address()));

        //                T_Card 輸入
        SIC_ObjectFile[] SIC_Ob_File_T = new SIC_ObjectFile[100];
        for(int q=0;q<100;q++)
            SIC_Ob_File_T[q] = new SIC_ObjectFile();
        num=0;
        int T_num=0;
        do
        {
            if(SIC_Ob_File_T[T_num].getT_Code_lengh()==0)  //若有T_card的長度為0 ,先放起始位置
            {
                do    //找到ob不為"      "
                {
                    if(Main_code[num].getmain_obcode().equals("      "))
                    {
                        num++;
                        if(num==result_total)
                            break;
                    }
                    else
                        break;
                }while(true);

                if(num==result_total)
                    break;
                //輸入起始位置
                SIC_Ob_File_T[T_num].setT_Code_Start(Integer.toHexString(Integer.valueOf(Main_code[num].getMain_address())));
                //輸入ob
                SIC_Ob_File_T[T_num].setT_Code(Main_code[num].getmain_obcode());
                num++;
                if(num==result_total)
                    break;
            }
            else
            {
                if(Main_code[num].getmain_obcode().equals("      "))
                {
                    T_num++;
                }
                else
                {
                    if(30 < SIC_Ob_File_T[T_num].getT_Code_lengh()+ Main_code[num].getmain_obcode().length()/2)
                    {
                        T_num++;
                        //輸入起始位置
                        SIC_Ob_File_T[T_num].setT_Code_Start(Integer.toHexString(Integer.valueOf(Main_code[num].getMain_address())));
                        //輸入ob
                        SIC_Ob_File_T[T_num].setT_Code(Main_code[num].getmain_obcode());

                        num++;
                        if(num==result_total)
                            break;

                    }
                    else
                    {
                        SIC_Ob_File_T[T_num].setT_Code(Main_code[num].getmain_obcode());

                        num++;
                        if(num==result_total)
                            break;
                    }
                }
            }
        }while(num!=result_total);

        for(int q=0;q<T_num;q++)
            file_Ob_Out.println(SIC_Ob_File_T[q]);

        file_Ob_Out.println(SIC_Ob_File.E_Ccard(Start_code.getAddress()));
        //          結束關閉檔案
        filenIn.close();
        file_List_Out.close();
        file_Ob_Out.close();
    }

}
