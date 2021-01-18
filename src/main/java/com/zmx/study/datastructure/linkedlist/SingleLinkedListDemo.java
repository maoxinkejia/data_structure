package com.zmx.study.datastructure.linkedlist;

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
        delete(s1);
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
    public static int getLength(HeroNode head) {
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

    static class SingleLinkedList {
        // 先初始化一个头节点，头结点不要动，不存放具体数据
        private HeroNode head = new HeroNode(0, "", "");

        public HeroNode getHead() {
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
            boolean finded = false;
            while (true) {
                // 这里第一次进来一定不为空，循环调用后可能为空
                if (temp == null) {
                    break;//已经找到链表的最后一个位置
                }
                if (temp.no == newHeroNode.no) {
                    finded = true;
                    break;
                }
                temp = temp.next;
            }

            if (!finded) {
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
            boolean finded = false;

            while (true) {
                if (temp.next == null) {
                    break; // 已经遍历结束
                }

                if (temp.next.no == no) {
                    finded = true;// 找到了待删除节点的前一个节点
                    break;
                }

                temp = temp.next;
            }

            if (!finded) {
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


