package com.zmx.study.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode h1 = new HeroNode(1, "1", "1");
        HeroNode h2 = new HeroNode(2, "2", "2");
        HeroNode h3 = new HeroNode(3, "3", "3");
        HeroNode h4 = new HeroNode(4, "4", "4");

        SingleLinkedList s1 = new SingleLinkedList();
        // 无序插入
//        add(h1, h2, h3, h4, s1);

        // 有序插入
        addByOrder(h1, h2, h3, h4, s1);

        //测试修改节点
        update(s1);

        // 测试删除节点
//        delete(s1);

        // 查找倒数第二个节点，预期为3
        findIndexNode(s1);

        // 反转链表
        reverseList(s1);

        reversePrint(s1.getHead());
    }

    private static void reverseList(SingleLinkedList s1) {
        System.out.println("反转后的链表为：");
        reverseList(s1.getHead());
        s1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void findIndexNode(SingleLinkedList s1) {
        HeroNode indexNode1 = findIndexNode(s1.getHead(), 1);
        System.out.println("倒数第一个节点为=" + indexNode1);
        HeroNode indexNode2 = findIndexNode(s1.getHead(), 3);
        System.out.println("倒数第三个节点为=" + indexNode2);
        HeroNode indexNode3 = findIndexNode(s1.getHead(), 6);
        System.out.println("倒数第六个节点为=" + indexNode3);
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void delete(SingleLinkedList s1) {
        s1.delete(2);
        s1.delete(4);
        s1.delete(4);
        s1.delete(1);
        s1.delete(3);
        System.out.println("删除后的结果");
        s1.list();
    }

    private static void update(SingleLinkedList s1) {
        HeroNode newHeroNode = new HeroNode(2, "222", "222");
        s1.update(newHeroNode);

        System.out.println("修改后的结果");
        s1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void add(HeroNode h1, HeroNode h2, HeroNode h3, HeroNode h4, SingleLinkedList s1) {
        s1.add(h1);
        s1.add(h4);
        s1.add(h2);
        s1.add(h3);
        s1.list();
    }

    private static void addByOrder(HeroNode h1, HeroNode h2, HeroNode h3, HeroNode h4, SingleLinkedList s1) {
        s1.addByOrder(h1);
        s1.addByOrder(h4);
        s1.addByOrder(h4);
        s1.addByOrder(h2);
        s1.addByOrder(h3);
        s1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    // 面试题
    // 获取到单链表的节点的个数（如果是带头结点的，要求不统计头结点）
    // 调用getLength()方法，传入参数为头结点参数，调用SingleLinkedList类的getHead()方法，将头节点作为参数传入getLength()中。
    private static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        HeroNode curr = head.next;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    // 查找单链表中的倒数第k个节点【新浪面试题】
    // 编写一个方法，接收head节点，同时接收一个index
    // index表示倒数第index个节点
    // 先把链表从头到尾遍历一下，得到链表总长度（getLength()）
    // 得到size后，从链表的第一个开始遍历，遍历（size - index）个，就可以得到
    // 如果找到就返回，否则返回null
    private static HeroNode findIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }

        // 第一次遍历先获取链表的长度
        int size = getLength(head);

        // 数据校验
        if (index <= 0 || index > size) {
            return null;
        }

        // 定义辅助变量，拿到第一个节点
        HeroNode curr = head.next;
        // 第二次遍历找 size - index 位置
        for (int i = 0; i < size - index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    // 单链表的反转【腾讯面试题，有点难度】
    // 先定义一个节点reverseHead = new HeroNode();
    // 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端，并指向下一个节点
    // 最后将原来的head.next = reverseHead.next
    private static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            // 链表为空，或链表只有一个节点无需反转
            return;
        }

        // 定义一个辅助的指针，帮助遍历原来的链表
        HeroNode curr = head.next;
        // 指向当前节点的下一个节点，如果不指向下一个节点，单链表会断掉
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端，并指向下一个节点
        while (curr != null) {
            HeroNode next = curr.next;//先暂时保存当前节点的下一个节点
            // 将curr的下一个节点指向新链表的最前端
            curr.next = reverseHead.next;
            // 将将reverse的头结点，指向curr
            reverseHead.next = curr;
            curr = next;// 让curr 后移
        }

        // 将head.next 指向 reverseHead.next 实现单链表反转
        head.next = reverseHead.next;
    }

    // 逆序打印单链表
    // 方式1：先将单链表进行反转操作，再遍历即可。但这样会破坏原链表结构，**不建议！**
    // 方式2：利用栈这个数据结构，将各个节点压入到栈中，利用栈先进后出的特点，实现了逆序打印
    private static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode curr = head.next;

        // 将链表的所有节点压入栈
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        // 将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    static class SingleLinkedList {
        // 先初始化一个头节点，头结点不要动，不存放具体数据
        private HeroNode head = new HeroNode(0, "", "");

        HeroNode getHead() {
            return head;
        }

        // 添加节点到单向链表
        // 当不考虑编号顺序时
        // 1.找到当前链表的最后一个节点
        // 2.将最后这个节点的next 指向 新的节点
        void add(HeroNode heroNode) {
            // 因为头节点不能动，需要一个辅助变量来帮助完成
            // 从head节点开始查找
            HeroNode temp = head;

            // temp.next != null说明不是最后一个节点
            while (temp.next != null) {
                // 没有找到时将temp后移，指向下一个节点
                temp = temp.next;
            }

            // temp为最后一个节点，将新添加进来的节点存放到temp.next中
            temp.next = heroNode;
        }

        // 有序添加节点
        void addByOrder(HeroNode heroNode) {
            // 因为头结点不能动，仍然使用辅助变量来帮助找到添加的位置
            // 因为是单向链表，我们找的temp是位于添加位置的前一个节点，否则插不去进去
            HeroNode temp = head;

            // 标识当前添加的编号是否已经存在，默认为false
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }

                if (temp.next.no == heroNode.no) {//当前编号已存在
                    flag = true;
                    break;
                }

                if (temp.next.no > heroNode.no) {// 找到需要存放的位置，在当前的temp后插入
                    break;
                }

                // 以上条件都不满足，后移，继续遍历查找
                temp = temp.next;
            }

            //找到temp
            if (flag) {// 当前编号已存在，不能添加
                System.out.println(heroNode.no + "已存在");
            } else {
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        // 更新链表
        void update(HeroNode newHeroNode) {
            if (head.next == null) {
                System.out.println("链表为空！");
                return;
            }

            HeroNode temp = head.next;
            boolean found = false;
            while (true) {
                // 这里第一次进来一定不为空，循环调用后可能为空
                if (temp == null) {
                    break;//已经找到链表的最后一个位置
                }
                if (temp.no == newHeroNode.no) {
                    found = true;
                    break;
                }
                temp = temp.next;
            }

            if (!found) {
                System.out.println("没有找到");
                return;
            }

            // 找到需要修改的节点，进行修改
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }

        // 删除节点
        void delete(int no) {
            HeroNode temp = head;
            // 标识是否找到待删除节点
            boolean found = false;

            while (true) {
                if (temp.next == null) {
                    break; // 已经遍历结束
                }

                if (temp.next.no == no) {
                    found = true;// 找到了待删除节点的前一个节点
                    break;
                }

                temp = temp.next;
            }

            if (!found) {
                System.out.println("没有找到");
                return;
            }

            // 找到了，可以删除，指向要删除的下一个节点
            temp.next = temp.next.next;
            System.out.printf("%d 已经被删除了", no);
            System.out.println();
        }

        // 显示链表
        void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }

            // 到这里说明head.next已经不为空，temp不为空
            HeroNode temp = head.next;
            while (temp != null) {
                // 判断链表是否已经到最后了
                // 输出节点信息，并将temp后移
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }


    static class HeroNode {
        int no;
        String name;
        String nickName;
        // 指向下一个节点
        HeroNode next;

        HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
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
}


