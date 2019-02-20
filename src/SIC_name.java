/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * 開始那行
 *
 * */
public class SIC_name
{
    private String address ;         //位置
    private String start_name ;      //標記
    private String start_instruct ;  //原始敘述||指令碼
    private String start_address ;   //起始位置

    /*           constructor 初值                      */
    public  SIC_name(String name,String instruct,String start_add)
    {
        start_name = name;
        start_instruct = "START";
        start_address = start_add;
        address = start_add;
    }

    public  SIC_name()
    {
        address = "0";
        start_name = "NULL";
        start_instruct = "START";
        start_address = "0";
    }

    /*           get name                          */
    public String getName()
    {

        return start_name;
    }

    /*           get private                            */
    public String getAddress()
    {
        String addr = Integer.valueOf(start_address,16).toString();
        return addr;
    }

    /*           tostring                            */
    public String toString()
    {
        /*                      output_address                       */
        String output_address = address.toUpperCase();
        for(int k=0; k< 4 - address.length() ; k++ )
            output_address = "0" + output_address;

        /*                    output_names                        */
        String output_name = start_name.toUpperCase();
        for(int k=0;k<8-start_name.length();k++)
            output_name = output_name+" ";

        /*                    output_instruct                        */
        String output_instruct = start_instruct.toUpperCase();
        for(int k=0;k<6-start_instruct.length();k++)
            output_instruct = output_instruct+" ";

        /*                    output_instruct                        */
        String output_start_address = start_address.toUpperCase();
        for(int k=0;k < 8 - (start_address.length());k++)
            output_start_address = output_start_address+" ";


        return (output_address +" "+ output_name +" "+ output_instruct +" "+ output_start_address);
    }
}
