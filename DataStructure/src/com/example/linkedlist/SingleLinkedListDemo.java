package com.example.linkedlist;

import java.sql.SQLOutput;
import java.util.Stack;

import static com.example.linkedlist.SingleLinkedList.*;

/**
 * 单链表实现水浒英雄排名
 *  SingleLinkedListDemo  测试
 *  SingleLinkedList     单链表
 *  HeroNode  节点
 * @author kay
 * @create 2022 - 03 - 29-9:35
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList list = new SingleLinkedList();
// 按照加入的顺序加入链表
//        list.add(hero4);
//        list.add(hero2);
//        list.add(hero1);
//        list.add(hero3);
// 按照编号  加入链表
        list.addByOrder(hero4);
        list.addByOrder(hero2);
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero3);
        list.list();

        // 测试根据编号修护英雄信息
        HeroNode hero5 = new HeroNode(4, "林哥", "豹子头");
        list.update(hero5);
        System.out.println("修改后的遍历");
        list.list();

//        list.del(1);
//        list.del(2);
//        list.del(4);
//        list.del(4);
//
//        System.out.println("删除后的遍历");
//        list.list();

        System.out.println("测试 求单链表的有效个数(不包含头结点)");

        HeroNode head = list.getHead();
        int length = getLength(head);
        System.out.println(length);

        System.out.println(findLastIndexNode(head, 1));

        System.out.println("测试单链表的反转：-------------------");

        reverseList(list.getHead());

        list.list();

        System.out.println("测试 单链表的逆序打印：");
        reversePrint(list.getHead());
    }
}

// 定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    // 先初始化一个头结点(头结点不要动 )，不存放具体的数据。
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    // 思路：当不考虑编号顺序时
    //  1.找到当前链表的最后节点
    //  2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        // 因为 head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    // 添加节点到单向链表
    // 思路：考虑编号顺序时
    //  按照英雄编号插入英雄，如果存在这个编号，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        // 因为 head节点不能动，因此我们需要一个辅助遍历temp
        // 因为单链表，因此我们找的temp是位于添加位置前的一个节点
        HeroNode temp = head;
        boolean flag = false;     // 表示编号是否存在，默认为false
        // 遍历链表找到temp
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){   // 位置找到了，就在temp的后面插入
                break;
            }
            if (temp.next.no == heroNode.no){   //  说明希望添加的英雄编号已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;
        }

        if (flag){   // 不能添加 说明编号存在
            System.out.printf("准备插入的英雄编号：%d 已经存在\n",heroNode.no);
        }else {
            // 插入到链表中 temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 根据 no 编号来修改 节点信息。
    public void update(HeroNode newHeroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;   //
        while(temp!=null){
            if (temp.no == newHeroNode.no){
                flag = true;  // 找到了
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号 %d 的英雄\n", newHeroNode.no);
        }
    }

    // 根据编号删除 英雄节点
    public void del(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        } else {
            System.out.printf("未找到编号 %d 的英雄", no);
        }
    }

    // 显示链表 遍历
    public void list(){

        if (head.next == null){
            System.out.println("为空");
            return;
        }

        HeroNode temp = head.next;

        while(true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     *  面试题 ：
     *      1. 获取到单链表的节点的个数（如果是带头结点链表，需要不统计头结点）
     *      2. 查找单链表中倒数第K个节点 （新浪面试题）
     *      3. 单链表的反转（腾讯面试题）
     *      4.从尾到头打印单链表（百度：要求方式一：反向遍历。方式二：Stack栈）
     *              方式一 ：先将单链表进行反转操作，然后在遍历即可  ----会破坏原来单链表的结构 不可取   不建议
     *              方式二 ：入栈 出栈  先入后出
     *
     *      5. 合并两个有序的单链表，合并之后的链表依然有序（课后练习）
     */

    /**
     * 1. 获取 单链表 节点个数
     * @param head  链表的头结点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length=0;
        HeroNode cur = head.next; // 没有统计头结点
        while(cur!=null){
            length++;
            cur  = cur.next;
        }
        return length;
    }

    /**
     * 1. 查找单链表中倒数第K个节点 （新浪面试题）
     * @param head  接收 head 节点
     * @param index 倒数第 index 个节点
     * @return 找到了 返回该节点， 未找到返回 null
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        int size = getLength(head);

        if (index<=0 || index > size){
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }
    /**
     * 3. 将单链表反转
     * @param head
     */
    public static void reverseList(HeroNode head){
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next==null){
            return;
        }

        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;  // 指向当前节点 [cur] 的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        // 遍历原来的链表，每遍历一个节点，就将其取出，放到新的链表reverseHead 的最前端。
        while (true){
            if (cur == null){
                break;
            }
            next = cur.next;  // 暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;   //  将cur 指向 reverseHead 的 下一个节点
            reverseHead.next = cur;     // 再讲 reverseHead 指向 cur 来完成 cur 插入到 新的链表的最前面
            cur = next; // cur 的 后移
        }
        head.next = reverseHead.next;

    }

    /**
     * 利用 栈 完成 单链表的逆序打印（不破坏单链表的结构）
     * @param head
     */
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

}

// 定义HeroNode ， 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;       // 英雄编号
    public String name;
    public String nickName;
    public HeroNode next;   // 指向下一个节点

    // 构造器
    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name=name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}