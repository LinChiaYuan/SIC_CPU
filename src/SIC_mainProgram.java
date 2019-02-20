/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * 主程式陣列
 *
 * */
public class SIC_mainProgram
{
    private String main_address ;     //位置
    private String main_name ;     	   //標記
    private String main_instruct ;    //原始敘述||指令碼
    private String main_use ;      //運作
    private String main_obcode ;      //目的碼

    /*           set private                            */
    public void setMain_address(String ma_address)
    {
        main_address=ma_address;
    }
    public void setMain_name(String ma_name)
    {
        main_name=ma_name;
    }
    public void setMain_instruct(String ma_instruct)
    {
        main_instruct=ma_instruct;
    }
    public void setMain_use(String ma_use)
    {
        main_use=ma_use;
    }
    public void setMain_obcode(String ma_obcode)
    {
        main_obcode=ma_obcode;
    }

    /*           get private                            */
    public String getMain_name()
    {
        return main_name;
    }
    public String getMain_use()
    {
        return main_use;
    }

    public String getMain_instruct()
    {
        return main_instruct;
    }

    public String getMain_address()
    {
        return main_address;
    }
    public String getmain_obcode()
    {
        return main_obcode;
    }

    /*           constructor 初值                      */
    public SIC_mainProgram()
    {
        main_address = "0";
        main_name = "      ";
        main_instruct = "      ";
        main_address = "      ";
        main_use ="    ";
    }

    /*           tostring                            */
    public String toString()
    {
        /*                      output_address                       */
        String output_address = Integer.toHexString(Integer.valueOf(main_address)).toUpperCase();
        for(int k=0; k< 4 - ( Integer.toHexString(Integer.valueOf(main_address)).length()) ; k++ )
            output_address = "0" + output_address;

        /*                    output_names                        */
        String output_name = main_name.toUpperCase();
        for(int k=0;k<8-main_name.length();k++)
            output_name = output_name+" ";

        /*                    output_instruct                        */
        String output_instruct = main_instruct.toUpperCase();
        for(int k=0;k<6-main_instruct.length();k++)
            output_instruct = output_instruct+" ";

        /*                    output_instruct                        */
        String output_main_use = main_use.toUpperCase();
        for(int k=0;k<18-main_use.length();k++)
            output_main_use = output_main_use+" ";

        /*                    output_instruct                        */
        String output_obcode = main_obcode.toUpperCase();
        for(int k=0; k < 8 - (main_obcode.length() ); k++)
            output_obcode = output_obcode+" ";

        return (output_address+" "+output_name+" "+output_instruct+" "+output_main_use+" "+output_obcode);
    }
}
