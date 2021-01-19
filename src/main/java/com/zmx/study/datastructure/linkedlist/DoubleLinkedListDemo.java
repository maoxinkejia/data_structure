package com.zmx.study.datastructure.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试  add update delete
        HeroNode2 h1 = new HeroNode2(1, "1", "1");
        HeroNode2 h2 = new HeroNode2(2, "2", "2");
        HeroNode2 h3 = new HeroNode2(3, "3", "3");
        HeroNode2 h4 = new HeroNode2(4, "4", "4");

        DoubleLinkedList d1 = new DoubleLinkedList();

        // 添加测试
//        add(h1, h2, h3, h4, d1);

        // 有序添加
        addByOrder(h1, h2, h3, h4, d1);

        // update
        update(d1);

        delete(d1);
    }

    private static void addByOrder(HeroNode2 h1, HeroNode2 h2, HeroNode2 h3, HeroNode2 h4, DoubleLinkedList d1) {
        d1.addByOrder(h3);
        d1.addByOrder(h3);
        d1.addByOrder(h1);
        d1.addByOrder(h2);
        d1.addByOrder(h2);
        d1.addByOrder(h4);
        d1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void delete(DoubleLinkedList d1) {
        d1.delete(4);
        d1.delete(3);
        d1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void update(DoubleLinkedList d1) {
        HeroNode2 newHeroNode = new HeroNode2(4, "444", "444");
        d1.update(newHeroNode);
        System.out.println("修改后的数据为");
        d1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static void add(HeroNode2 h1, HeroNode2 h2, HeroNode2 h3, HeroNode2 h4, DoubleLinkedList d1) {
        d1.add(h1);
        d1.add(h4);
        d1.add(h2);
        d1.add(h3);
        d1.list();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static class DoubleLinkedList {
        // 先初始化一个头结点，头结点不要动，不存放具体的数据
        private HeroNode2 head = new HeroNode2(0, "", "");

        // 返回头结点
        HeroNode2 getHead() {
            return head;
        }

        void addByOrder(HeroNode2 heroNode2) {
            HeroNode2 tmp = head;
            while (tmp.next != null) {
                if (tmp.next.no == heroNode2.no) {
                    System.out.println("当前编号以插入，不能重复插入，no=" + heroNode2.no);
                    return;
                }

                if (tmp.next.no > heroNode2.no) {
                    break;
                }

                tmp = tmp.next;
            }

            heroNode2.pre = tmp;
            heroNode2.next = tmp.next;
            if (heroNode2.next != null) {
                tmp.next.pre = heroNode2;
            }
            tmp.next = heroNode2;

            System.out.printf("no=%d，插入成功\n", heroNode2.no);
        }

        void add(HeroNode2 heroNode2) {
            HeroNode2 temp = head;

            // 遍历链表，找到最后的节点
            while (temp.next != null) {
                // 指针后移
                temp = temp.next;
            }

            temp.next = heroNode2;
            heroNode2.pre = temp;
        }

        void update(HeroNode2 heroNode2) {
            if (head.next == null) {
                System.out.println("链表为空~");
                return;
            }

            HeroNode2 tmp = head.next;
            boolean found = false;

            while (tmp != null) {
                if (heroNode2.no == tmp.no) {
                    found = true;
                    break;
                }

                tmp = tmp.next;
            }

            if (!found) {
                System.out.println("没有找到对应的节点，no=" + heroNode2.no);
                return;
            }

            tmp.name = heroNode2.name;
            tmp.nickName = heroNode2.nickName;
        }

        void delete(int no) {
            HeroNode2 tmp = head.next;
            boolean found = false;

            while (tmp != null) {
                if (tmp.no == no) {
                    found = true;
                    break;
                }

                tmp = tmp.next;
            }

            if (!found) {
                System.out.println("没有找到指定节点");
                return;
            }

            tmp.pre.next = tmp.next;
            // 若刚好tmp为最后一个节点时，tmp.next为null
            if (tmp.next != null) {
                tmp.next.pre = tmp.pre;
            }
        }

        void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }

            HeroNode2 tmp = head.next;
            while (tmp != null) {
                System.out.println(tmp);
                tmp = tmp.next;
            }
        }
    }

    static class HeroNode2 {
        int no;
        String name;
        String nickName;
        // 指向下一个节点,默认为null
        HeroNode2 next;
        // 指向前一个节点，默认为null
        HeroNode2 pre;

        HeroNode2(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
