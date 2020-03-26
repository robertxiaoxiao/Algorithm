package BasicDStructure.Stack;

/*
    数组实现  动态扩容栈
 */
public class DynamicStack {

    //容积
    public   int  initCap;

    //存储数组
    public  int[] data;

    //栈头指针
    public  int idx=-1;

    //默认构造
    public DynamicStack() {
        this.initCap = 8;
        this.data=new int[initCap];
    }

    public DynamicStack(int initCap) {
        this.initCap = initCap;
        this.data=new int[initCap];
    }

    /*
    将调整部分抽象成独立的方法
     */
    public void push(int val){

        if(idx+1<initCap)
        {
            dirpush(val);
        }
        else
        {
            //动态扩容
            reshape();
            dirpush(val);
        }
    }
    /*
        出栈是需要栈为空  抛出异常
        否则抛出栈头节点值，指正回退一
     */
    public int poll() throws Exception {

        if(idx==-1)
            throw  new Exception("the stack is empty");

        int temp=this.data[idx];

        idx-=1;
        return temp;

    }

    public void dirpush(int val){
            idx++;
            data[idx]=val;
    }
    /*
      扩容并 迁移数组
     */
    public  void reshape(){
        this.initCap=this.initCap*2;
        int[] temp=new int[this.initCap];

        for(int i=0;i<data.length;i++)
            temp[i]=data[i];

        this.data=temp;
    }

    public  void print(){
        int temp=idx;
        while(temp>=0)
        {
            System.out.print(this.data[temp--]+"    ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        DynamicStack dstack= new DynamicStack(8);
        for(int i =0;i<8;i++)
            dstack.push(i);

        dstack.print();

        for(int i=10;i<25;i++)
            dstack.push(i);

        dstack.print();

        for(int i=0;i<10;i++)
            dstack.poll();

        dstack.print();

        for(int i=0;i<40;i++)
            dstack.push(i);

        dstack.print();
    }

}
