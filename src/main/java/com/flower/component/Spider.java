package com.flower.component;

import com.flower.entity.Flower;
import com.flower.service.FlowerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鲜花网站爬虫
 */
@Component
public class Spider {
    @Resource
    private FlowerService flowerService;

    /**
     * 爬取最新的鲜花
     * @param url 鲜花路由
     */
    public boolean spiderNewFlower(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements hrefs = document.select(".li_div_box_cur > a");            // 获取购买地址
        Elements images = document.select(".li_div_box_cur > a img");       // 获取商品图片
        Elements titles = document.select(".li_div_box_cur > p > a");       // 获取商品标题
        Elements prices = document.select(".li_div_box_cur > p > span > font"); // 获取商品价格
        Elements descriptions = document.select(".li_div_box_cur > p.desc > a");// 获取商品描述
        Elements sales = document.select(".li_div_box_cur > p.li_bar > .salenum");  // 获取商品销量
        List<Flower> flowers = new ArrayList<>();
        Flower flower = flowerService.getLast();
        for (int j = 0; j < hrefs.size(); j++) {
            Element hrefE = hrefs.get(j);
            String href = hrefE.attr("href");
            if (flower != null && href.equals(flower.getHref())) {
                break;
            }
            Element imageE = images.get(j);
            String image = imageE.attr("src");
            Element titleE = titles.get(j);
            String title = titleE.text();
            Element priceE = prices.get(j);
            Double price = Double.valueOf(priceE.text());
            Element descriptionE = descriptions.get(j);
            String description = descriptionE.text();
            Element saleE = sales.get(j);
            Integer sale = Integer.valueOf(saleE.text().replace("人已付款", ""));
            flowers.add(new Flower(null, title, price, image, href, sale, description));
        }
        if (flowers.size() > 0)
            return flowerService.addFlowers(flowers);
        else
            return false;
    }

    /**
     * 爬取所有鲜花
     * @param url 鲜花路由
     */
    public boolean spiderAllFlower(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements hrefs = document.select(".li_div_box_cur > a");            // 获取购买地址
        Elements images = document.select(".li_div_box_cur > a img");       // 获取商品图片
        Elements titles = document.select(".li_div_box_cur > p > a");       // 获取商品标题
        Elements prices = document.select(".li_div_box_cur > p > span > font"); // 获取商品价格
        Elements descriptions = document.select(".li_div_box_cur > p.desc > a");// 获取商品描述
        Elements sales = document.select(".li_div_box_cur > p.li_bar > .salenum");  // 获取商品销量
        List<Flower> flowers = new ArrayList<>();
        Flower flower = flowerService.getLast();
        for (int j = hrefs.size() - 1; j >= 0; j--) {
            Element hrefE = hrefs.get(j);
            String href = hrefE.attr("href");
            if (flower != null && href.equals(flower.getHref())) {
                break;
            }
            Element imageE = images.get(j);
            String image = imageE.attr("src");
            Element titleE = titles.get(j);
            String title = titleE.text();
            Element priceE = prices.get(j);
            Double price = Double.valueOf(priceE.text());
            Element descriptionE = descriptions.get(j);
            String description = descriptionE.text();
            Element saleE = sales.get(j);
            Integer sale = Integer.valueOf(saleE.text().replace("人已付款", ""));
            flowers.add(new Flower(null, title, price, image, href, sale, description));
        }
        if (flowers.size() > 0)
            return flowerService.addFlowers(flowers);
        else
            return false;
    }
}
