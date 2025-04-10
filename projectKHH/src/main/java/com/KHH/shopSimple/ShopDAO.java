package com.KHH.shopSimple;

import com.KHH.main.DBManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShopDAO {
    private static final ShopDAO SDAO = new ShopDAO();

    public static ShopDAO getSdao() {
        return SDAO;
    }

    public static ArrayList<ShopSimpleDTO> simpleLists;
    public static ArrayList<ShopDetailDTO> getDetail;

    private ShopDAO() {
    }


    public ArrayList<ShopSimpleDTO> ShowSimpleLists(HttpServletRequest request) {
        simpleLists = new ArrayList<>();
        String sql = "select * from SHOP_INFO ";
        String addrtype = request.getParameter("data-region");

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ShopSimpleDTO simpleDTO = new ShopSimpleDTO(
                        rs.getInt("shop_no"),
                        rs.getString("shop_name"),
                        rs.getString("shop_addrtype"),
                        rs.getString("shop_tel"),
                        rs.getString("shop_opentime"));
                simpleLists.add(simpleDTO);

                System.out.println("Read Success!!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                DBManager.close(con, pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return simpleLists;
    }

    public ShopDetailDTO DetailShopInfo(HttpServletRequest request) {
        int no = Integer.parseInt(request.getParameter("shop_no"));
        // String sql = "select * from SHOP_INFO where shop_no=?";
        ShopDetailDTO detailDTO = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBManager.connect();
            pst = con.prepareStatement("select * from SHOP_INFO where SHOP_NO=?");
            pst.setInt(1, no);
            rs = pst.executeQuery();
            while (rs.next()) {
                detailDTO = new ShopDetailDTO();
                detailDTO.setShop_owner(rs.getString("shop_owner"));
                detailDTO.setShop_name(rs.getString("shop_name"));
                detailDTO.setShop_addr(rs.getString("shop_addr"));
                detailDTO.setShop_addrtype(rs.getString("shop_addrtype"));
                detailDTO.setShop_tel(rs.getString("shop_tel"));
                detailDTO.setShop_content(rs.getString("shop_content"));
                detailDTO.setShop_opentime(rs.getString("shop_opentime"));
                System.out.println("Shop Detail Read Success!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pst, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detailDTO;
    }

    //    포인터 종속 가게 리스트 helped by GPT
    public ArrayList<ShopDetailDTO> getShopsByRegion(String region) {
        ArrayList<ShopDetailDTO> shopList = new ArrayList<>();
        String sql = "SELECT * FROM SHOP_INFO WHERE shop_addrtype = ?";
        String imgSQL = "select * from SHOP_IMAGE where shop_no=? and image_type=?";

        String imgType = "main";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String shop_no;

        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            System.out.println("실행된 SQL: " + pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ShopDetailDTO shop = new ShopDetailDTO();
                shop.setShop_no(rs.getInt("shop_no"));
                shop.setShop_owner(rs.getString("shop_owner"));
                shop.setShop_name(rs.getString("shop_name"));
                shop.setShop_addr(rs.getString("shop_addr"));
                shop.setShop_addrtype(rs.getString("shop_addrtype"));
                shop.setShop_tel(rs.getString("shop_tel"));
                shop.setShop_content(rs.getString("shop_content"));
                shop.setShop_opentime(rs.getString("shop_opentime"));

                pstmt = con.prepareStatement(imgSQL);
                pstmt.setInt(1, rs.getInt("shop_no"));
                pstmt.setString(2, imgType);
                rs2 = pstmt.executeQuery();
                if (rs2.next()) {
                    shop.setShop_img(rs2.getString("shop_image"));
                }
                shopList.add(shop);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.close(con, pstmt, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return shopList;
    }


}
