package com.highio.shoping.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Locale;

public class CookieUtil {
    public static String getCookieValue(HttpServletRequest request, String cookieName){
        return getCookieValue(request,cookieName,false);

    }
    public static String getCookieValue(HttpServletRequest request, String cookieName,boolean isDecoder){
        Cookie[] cookieList = request.getCookies();
        if(cookieList == null || cookieName == null){
            return null;
        }

        String retValue = null;
        try{
            for(int i=0;i<cookieList.length;i++){
                if(cookieList[i].getName().equals(cookieName)){
                    if(isDecoder){
                        retValue = URLDecoder.decode(cookieList[i].getValue(),"UTF-8");
                    }else{
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retValue;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName,String encodeString){
        Cookie[] cookieList = request.getCookies();
        if(cookieList == null || cookieName == null){
            return null;
        }

        String retValue = null;
        try{
            for(int i=0;i<cookieList.length;i++){
                if(cookieList[i].getName().equals(cookieName)){
                    retValue = URLDecoder.decode(cookieList[i].getValue(),encodeString);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retValue;
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue){
        setCookie(request,response,cookieName,cookieValue,-1);
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage){
        setCookie(request,response,cookieName,cookieValue,cookieMaxage,false);
    }
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,boolean isEncode){
        setCookie(request,response,cookieName,cookieValue,-1,isEncode);
    }
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,isEncode);
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,encodeString);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,String cookieName){
        doSetCookie(request,response,cookieName,"",-1,false);
    }

    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
        try {
            if(cookieValue == null){
                cookieValue = "";
            }else if(isEncode){
                cookieValue = URLEncoder.encode(cookieValue,"UTF-8");
            }
            Cookie cookie = new Cookie(cookieName,cookieValue);
            if(cookieMaxage>0){
                cookie.setMaxAge(cookieMaxage);
            }
            if(request != null){
                String domainName = getDomainName(request);
                if(!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        try {
            if(cookieValue == null){
                cookieValue = "";
            }else{
                cookieValue = URLEncoder.encode(cookieValue,encodeString);
            }
            Cookie cookie = new Cookie(cookieName,cookieValue);
            if(cookieMaxage>0){
                cookie.setMaxAge(cookieMaxage);
            }
            if(request != null){
                String domainName = getDomainName(request);
                if(!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final String getDomainName(HttpServletRequest request){
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if(serverName == null || serverName.equals("")){
            domainName = "";
        }else{
            serverName = serverName.toLowerCase();
            if(serverName.startsWith("http://")){
                serverName = serverName.substring(7);
            }
            int endIndex = serverName.length();
            if(serverName.contains("/")){
                endIndex = serverName.indexOf("/");
            }
            serverName = serverName.substring(0,endIndex);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if(len >3){
                domainName = domains[len-3] + "." + domains[len-2]+"."+domains[len-1];
            }else if(len <=3 && len>1){
                domainName = domains[len-2]+"."+domains[len-1];
            }else{
                domainName = serverName;
            }
        }
        if(domainName != null && domainName.indexOf(":") > 0){
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }


}
