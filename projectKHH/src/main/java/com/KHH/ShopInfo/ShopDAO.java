package com.KHH.ShopInfo;

import com.KHH.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopDAO {
    public static void getShop(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String num = req.getParameter("no");
        String shopSQL = "select * from shop_info where shop_no=?";
        String imgSQL = "select * from SHOP_IMAGE where shop_no=? order by image_type";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(shopSQL);
            pstmt.setString(1, num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ShopDTO shop = new ShopDTO();
                shop.setShop_no(rs.getInt(1));
                shop.setShop_owner(rs.getString(2));
                shop.setShop_name(rs.getString(3));
                shop.setShop_addr(rs.getString(4));
                shop.setShop_tel(rs.getString(5));
                shop.setShop_content(rs.getString(6));
                shop.setShop_opentime(rs.getString(7));
                shop.setShop_addrtype(rs.getString(8));

                pstmt = con.prepareStatement(imgSQL);
                pstmt.setInt(1, rs.getInt(1));
                rs2 = pstmt.executeQuery();
                if (rs2.next()) {
                    shop.setMain_image(rs2.getString(2));
                }
                ArrayList<String> subfiles = new ArrayList<>();
                while (rs2.next()) {
                    subfiles.add(rs2.getString(2));
                }
                shop.setSub_image(subfiles);


                req.setAttribute("shop", shop);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void showAllShop(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String shopSQL = "select * from SHOP_INFO";
        String imgSQL = "select * from SHOP_IMAGE where shop_no=? order by image_type";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(shopSQL);
            rs = pstmt.executeQuery();
            ShopDTO shop = null;
            ArrayList<ShopDTO> shops = new ArrayList<>();
            while (rs.next()) {
                System.out.println("DB쿼리 수행됨");
                shop = new ShopDTO();
                shop.setShop_no(rs.getInt(1));
                shop.setShop_owner(rs.getString(2));
                shop.setShop_name(rs.getString(3));
                shop.setShop_addr(rs.getString(4));
                shop.setShop_tel(rs.getString(5));
                shop.setShop_content(rs.getString(6));
                shop.setShop_opentime(rs.getString(7));
                shop.setShop_addrtype(rs.getString(8));

                pstmt = con.prepareStatement(imgSQL);
                pstmt.setInt(1, rs.getInt(1));
                rs2 = pstmt.executeQuery();
                if (rs2.next()) {
                    shop.setMain_image(rs2.getString(2));
                }
                ArrayList<String> subfiles = new ArrayList<>();
                while (rs2.next()) {
                    subfiles.add(rs2.getString(2));
                }
                shop.setSub_image(subfiles);    // img1-2.jpg,img1-3.jpg,img1-1.jpg

                shops.add(shop);
            }
            System.out.println(shops);
            req.setAttribute("shops", shops);
            req.setAttribute("shop", shop);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
//                rs2.close();
        }
    }


    public static void showAllMenu(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement("select * from menu_info where menu_shop=?");
            System.out.println("shop_no: " + req.getParameter("no"));
            pstmt.setString(1, req.getParameter("no"));
            rs = pstmt.executeQuery();
            MenuDTO menu = null;
            ArrayList<MenuDTO> menus = new ArrayList<>();
            while (rs.next()) {
                System.out.println("DB쿼리 수행됨");
                menu = new MenuDTO();
                menu.setMenu_no(rs.getInt(1));
                menu.setMenu_shop(rs.getString(2));
                menu.setMenu_type(rs.getString(3));
                menu.setMenu_name(rs.getString(4));
                menu.setMenu_price(rs.getString(5));

                menus.add(menu);
            }
            System.out.println(menus);
            req.setAttribute("menus", menus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }

    }

    public static void showAllReview(HttpServletRequest req) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String reviewSQL = "select * from REVIEW_INFO ri, REVIEW_IMAGE rimg where ri.review_no = rimg.REVIEW_NO and ri.review_shop = ?";

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(reviewSQL);
            pstmt.setString(1, req.getParameter("no"));
            rs = pstmt.executeQuery();
            ReviewDTO review = null;
            ArrayList<ReviewDTO> reviews = new ArrayList<>();
            while (rs.next()) {
                System.out.println("DB쿼리 수행됨");
                review = new ReviewDTO();
                review.setReview_no(rs.getInt(1));
                review.setReview_shop(rs.getInt(2));
                review.setReview_content(rs.getString(3));
                review.setReview_date(rs.getString(4));
                review.setReview_nickname(rs.getString(5));
                review.setReview_star(rs.getInt(6));
                review.setReview_image(rs.getString(8));

                reviews.add(review);
            }
            System.out.println(reviews);

            req.setAttribute("review", reviews);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }


    }


}
