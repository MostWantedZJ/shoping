package com.highio.shoping.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("/hello")
    public String func(Model model){
        model.addAttribute("name","jian");
        return "testWebPage";
    }

    @RequestMapping("this")
    public String getValue(){
        return "123";
    }

    @AllArgsConstructor
    class Good{
        int id;
        int kg;
        int price;
    }
    public int go(){
        List<Good> list = new ArrayList<>();
        List<Good> path = new ArrayList<>();
        List<List<Good>> ans = new ArrayList<>();
        list.add(new Good(1,5,5));
        list.add(new Good(2,10,8));
        list.add(new Good(3,3,4));
        list.add(new Good(4,4,2));
        list.add(new Good(5,1,10));

        dfs(list,path,ans);
        int maxValue = 0;

        for(List<Good> tempPath:ans){
            System.out.print("feasible combination item id: ");
            for(Good tempGood:tempPath){
                System.out.print(tempGood.id+" ");
            }
            System.out.println();
            int v = getPrice(tempPath);
            if(v >= maxValue){
                maxValue = v;
                path = tempPath;
            }
        }
        System.out.print("the items'ids in combination with max price: ");
        for(Good tempGood:path){
            System.out.print(tempGood.id+" ");
        }
        System.out.println();
        return maxValue;
    }
    public int getKg(List<Good> path){
        int ans = 0;
        for(Good temp:path){
            ans += temp.kg;
        }
        return ans;
    }
    public int getPrice(List<Good> path){
        int ans = 0;
        for(Good temp:path){
            ans += temp.price;
        }
        return ans;
    }

    public void dfs(List<Good> list,List<Good> path,List<List<Good>> ans){
        if(list == null){
            return;
        }
        int currentKg = getKg(path);
        int currentPirce = getPrice(path);
        if( currentKg> 7){
            return;
        }else{
            if(!ans.contains(path))
                ans.add(new ArrayList<>(path));
        }
        for(int i=0;i<list.size();i++){
            Good currentGood = list.get(i);

            path.add(currentGood);
            List<Good> list1 = new ArrayList<>(list);
            list1.remove(currentGood);
            dfs(list1,path,ans);
            path.remove(currentGood);
        }
    }

    public static void main(String[] args) {
        TestController testController = new TestController();
        System.out.println("Max price is: "+testController.go());

    }



}
