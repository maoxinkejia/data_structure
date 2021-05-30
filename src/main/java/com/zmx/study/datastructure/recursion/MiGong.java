package com.zmx.study.datastructure.recursion;

public class MiGong {
    public static void main(String[] args) {
        int[][] maze = initWall();

        // 设置额外挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
//        maze[1][1] = 1;
//        maze[1][2] = 1;

        printMaze(maze);

//        setWay(maze, 1, 1);
        setWay2(maze, 1, 1);

        System.out.println();
        System.out.println("小球最终走过的路线为：");
        printMaze(maze);
    }

    /**
     * 1.使用递归来给小球找路
     * 2.i,j表示从哪个位置开始出发，如果找到路就返回true，如果找不到就返回false
     * 3.如果小球能找到map[7][6]的位置，则说明通路找到
     * 4.约定：当map[i][j] = 0 时表示该点没有走过，当为1表示墙，2表示通路可以走，3表示该点已经走过，但是走不通
     * 5.在走迷宫时，需要确定一个策略（方法） 下 -->> 右 -->> 上 -->> 左，如果该点走不通再回溯
     *
     * @param maze 迷宫地图
     * @param i    小球出发的横坐标
     * @param j    小球出发的纵坐标
     * @return 如果能找到通路则return true，若找不到通路，则return false
     */
    private static boolean setWay(int[][] maze, int i, int j) {
        // 二位数组长度是8,7，对应index就是7,6，最外层是墙，所以真实的终点位置是6,5，也就是outerMaxIndex-1,innerMaxIndex-1
        int outerMaxIndex = maze.length - 1;
        int innerMaxIndex = maze[outerMaxIndex].length - 1;

        // 此处为跳出递归的关键判断条件，若到达最终点，则需要跳出递归循环，否则会一直死循环递归
        if (maze[outerMaxIndex - 1][innerMaxIndex - 1] == 2) {
            return true;
        }

        if (maze[i][j] != 0) {
            // 当前这个点走过，有可能是1,2,3
            return false;
        }

        // 这个点还没有走过，先假定这个点可以走通
        maze[i][j] = 2;
        if (setWay(maze, i + 1, j)) {// 往下走
            return true;
        } else if (setWay(maze, i, j + 1)) {// 向右走
            return true;
        } else if (setWay(maze, i - 1, j)) {// 往上走
            return true;
        } else if (setWay(maze, i, j - 1)) {// 往左走
            return true;
        } else {
            // 此路不通，是死路
            maze[i][j] = 3;
            return false;
        }
    }

    /**
     * 此方法为换一种前进策略：改成 上 -> 右 -> 下 -> 左
     */
    private static boolean setWay2(int[][] maze, int i, int j) {
        int index1 = maze.length - 1;
        int index2 = maze[index1].length - 1;

        if (maze[index1 - 1][index2 - 1] == 2) {
            return true;
        }

        if (maze[i][j] != 0) {
            return false;
        }

        maze[i][j] = 2;
        if (setWay2(maze, i - 1, j)) {// 向上走
            return true;
        } else if (setWay2(maze, i, j + 1)) {// 向右走
            return true;
        } else if (setWay2(maze, i + 1, j)) {// 向下走
            return true;
        } else if (setWay2(maze, i, j - 1)) {// 向左走
            return true;
        } else {
            maze[i][j] = 3;
            return false;
        }
    }

    private static void printMaze(int[][] maze) {
        for (int[] outerArr : maze) {
            for (int innerArr : outerArr) {
                System.out.print(innerArr + "\t");
            }
            System.out.println();
        }
    }


    // 创建二位数组模拟迷宫, 并将上下左右都设置成围墙
    private static int[][] initWall() {
        int[][] maze = new int[8][7];

        // 将最上层和最下层都置为1，模拟墙
        for (int i = 0; i < maze[0].length; i++) {
            maze[0][i] = 1;
            maze[maze.length - 1][i] = 1;
        }

        // 将最左侧和最右侧都置为1，模拟墙
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][maze[i].length - 1] = 1;
        }

        return maze;
    }
}
