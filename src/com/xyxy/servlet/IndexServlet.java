package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.*;
import com.xyxy.service.*;


import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends BaseServlet {
    private SongService songService = new SongService();
    private CdService cdService = new CdService();
    private SingerService singerService = new SingerService();
    private VideoService videoService = new VideoService();
    private CommentService commentService = new CommentService();
    private UserService userService = new UserService();

    //查询后显示部分数据
    public void queryPlayCount(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //获取歌曲热度排行榜
        List<Song> songList = songService.queryPlayCount(10);
        HttpSession session = request.getSession();
        session.setAttribute("songList1", songList);
        //获取下载排行榜
        List<Song> songList1 = songService.queryDownLoad(10);
        session.setAttribute("songList2", songList1);
        //获取热议排行榜
        List<Song> songList2 = songService.queryDiscuss(10);
        session.setAttribute("songList3", songList2);
        request.getRequestDispatcher("index/index.jsp").forward(request, response);
    }

    //查询热度榜后显示所有数据
    public void queryAllPlay(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        //查询所有的歌曲按照播放次数来排列
        List<Song> songList = songService.queryAllPlay();
        //将查询到的数据存入session作用域中
        HttpSession session = request.getSession();
        session.removeAttribute("songList");
        session.setAttribute("songList", songList);
        request.getRequestDispatcher("index/song_range.jsp").forward(request, response);
    }

    //查询下载榜后显示所有数据
    public void queryAllDowm(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //查询所有的歌曲按照下载次数来排列
        List<Song> songList = songService.queryAllDowm();
        //将查询到的数据存入session作用域中
        HttpSession session = request.getSession();
        session.removeAttribute("songList");
        session.setAttribute("songList", songList);
        request.getRequestDispatcher("index/song_range.jsp").forward(request, response);
    }

    //查询热议榜后显示所有数据
    public void queryAllDis(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //查询所有的歌曲按照评论次数来排列
        List<Song> songList = songService.queryAllDis();
        //将查询到的数据存入session作用域中
        HttpSession session = request.getSession();
        session.removeAttribute("songList");
        session.setAttribute("songList", songList);
        request.getRequestDispatcher("index/song_range.jsp").forward(request, response);
    }

    //点击歌曲会跳到歌曲详情页面，同时显示该歌曲所有的评论
    public void querySongDetail(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //获取歌曲的sid
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        //调用service方法查询符合的歌曲，并查询歌曲评论
        Song song = songService.checkById(sid);
        List<Comment> commentList = commentService.queryComBySid(sid);
        //对获取到的评论将用户id转换成用户名
        for (Comment comment : commentList) {
            //遍历获取到的评论
            Integer uid = comment.getUid();
            //查询用户获取用户名
            Users users = userService.checkById(uid);
            comment.setUsers(users);
        }
        //将查询到的歌曲传入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("song", song);
        session.setAttribute("comList", commentList);
        //获取歌曲的cid
        //调用service方法查询符合的歌曲
        Cd cd = cdService.findCdById(song.getCdId());
        //将查询到的专辑传入作用域中
        session.setAttribute("Cd", cd);
        //跳转到详情页面
        response.sendRedirect("index/song_detail.jsp");
    }

    //点击歌手会跳到歌手详情页面
    public void querySingerDetail(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        //获取歌手的姓名
        String singerName =request.getParameter("singerName");
        //调用service方法查询符合的歌曲
        List<Song> song = songService.checkBySingerName(singerName);
        Singer singer=singerService.checkBySingerName(singerName);
        //将查询到的歌曲传入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("songs", song);
        session.setAttribute("singers", singer);
        //跳转到详情页面
        response.sendRedirect("index/singer_detail.jsp");
    }

    //点击专辑会跳到专辑详情页面
    public void queryCdDetail(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        //获取专辑id
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        //调用service方法查询符合的歌曲
        List<Song> song = songService.checkByCId(cid) ;
        //将查询到的歌曲传入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("songcds", song);
        //调用service方法查询符合的歌曲
        Cd cd = cdService.findCdById(cid);
        //将查询到的专辑传入作用域中
        session.setAttribute("Cd", cd);
        //跳转到详情页面
        response.sendRedirect("index/cd_detail.jsp");
    }

    //根据歌名查询相关的歌曲
    public void checkBySong(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取歌名
        String songname = request.getParameter("song");
        //调用方法来查询
        Song song = songService.checkBySongName(songname);
        List<Comment> commentList = commentService.queryComBySid(song.getSid());
        //对获取到的评论将用户id转换成用户名
        for (Comment comment : commentList) {
            //遍历获取到的评论
            Integer uid = comment.getUid();
            //查询用户获取用户名
            Users users = userService.checkById(uid);
            comment.setUsers(users);
        }
        //将查询到的歌曲传入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("song", song);
        session.setAttribute("comList", commentList);
        //调用service方法查询符合的歌曲
        Cd cd = cdService.findCdById(song.getCdId());
        //将查询到的专辑传入作用域中
        session.setAttribute("Cd", cd);
        if (song != null) {
            response.getWriter().write(JSON.toJSONString("ok"));
        } else {
            response.getWriter().write(JSON.toJSONString("fail"));
        }
    }

    //查询所有歌手并显示
    public void queryAllSinger(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //调用service方法查询所有歌手
        List<Singer> singerList = singerService.querySinger();
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("singerList", singerList);
        //跳到歌手展示页面
        request.getRequestDispatcher("index/singer_list.jsp").forward(request, response);
    }

    //查询华语男歌手歌手并显示
    public void queryManSinger(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //调用service方法查询所有华语男歌手
        List<Singer> singerList = singerService.queryManSinger();
        //将查到的数据存入作用域中
        request.setAttribute("singerList", singerList);
        //跳到歌手展示页面
        request.getRequestDispatcher("index/singer_list.jsp").forward(request, response);
    }

    //查询华语女歌手歌手并显示
    public void queryWomanSinger(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        //调用service方法查询所有华语女歌手
        List<Singer> singerList = singerService.queryWomanSinger();
        //将查到的数据存入作用域中
        request.setAttribute("singerList", singerList);
        //跳到歌手展示页面
        request.getRequestDispatcher("index/singer_list.jsp").forward(request, response);
    }

    //查询华语女歌手歌手并显示
    public void queryZhSinger(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        //调用service方法查询所有华语女歌手
        List<Singer> singerList = singerService.queryZhSinger();
        //将查到的数据存入作用域中
        request.setAttribute("singerList", singerList);
        //跳到歌手展示页面
        request.getRequestDispatcher("index/singer_list.jsp").forward(request, response);
    }
    //根据歌手首字母来查询歌手
    public void queryByFirstCode(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        //获取歌手的首字母
        String firstCode = request.getParameter("firstCode");
        //调用查询service方法
        List<Singer> singer = singerService.querySingerByCode(firstCode);
        //存入作用域中
        request.setAttribute("singerList", singer);
        //跳到歌手展示页面
        request.getRequestDispatcher("index/singer_list.jsp").forward(request, response);
    }


    //查询所有歌MV并显示
    public void queryAllMv(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //调用service方法查询所有MV
        List<Video> videoList = videoService.queryVideo();
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("videoList", videoList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/mv_list.jsp").forward(request, response);
    }

    //根据地区查询所有歌MV并显示
    public void queryByVcity(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //获取需要根据的条件地区
        String vcity = request.getParameter("vcity");
        //调用service方法查询所有MV
        List<Video> videoList = videoService.queryByVcity(vcity);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("videoList", videoList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/mv_list.jsp").forward(request, response);
    }

    //根据是否热门查询所有歌MV并显示
    public void queryPopular(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //获取需要根据的条件地区
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //调用service方法查询所有MV
        List<Video> videoList = videoService.queryPopular(popular);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("videoList", videoList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/mv_list.jsp").forward(request, response);
    }

    //根据年代查询所有歌MV并显示
    public void queryVyear(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取需要根据的条件--年代
        Integer vyear = Integer.valueOf(request.getParameter("vyear"));
        //调用service方法查询所有MV
        List<Video> videoList = videoService.queryVyear(vyear);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("videoList", videoList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/mv_list.jsp").forward(request, response);
    }

    //点击视频图片来播放MV
    public void queryByVid(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取要播放的视频id
        Integer vid = Integer.valueOf(request.getParameter("vid"));
        //根据vid查询相关视频
        Video video = videoService.findVideoById(vid);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("video", video);
        //跳到MV展示页面
        request.getRequestDispatcher("index/mv_play.jsp").forward(request, response);
    }


    //查询所有的专辑并显示
    public void queryAllCd(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //调用service方法查询所有MV
        List<Cd> cdList = cdService.queryCd();
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("cdList", cdList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/cd_list.jsp").forward(request, response);
    }

    //根据地区查询所有歌专辑并显示
    public void queryByCity(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取需要根据的条件地区
        String city = request.getParameter("city");
        //调用service方法查询所有MV
        List<Cd> cdList = cdService.queryByCity(city);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("cdList", cdList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/cd_list.jsp").forward(request, response);
    }

    //根据是否热门查询所有专辑并显示
    public void queryCpopular(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //获取需要根据的条件地区
        Integer popular = Integer.valueOf(request.getParameter("popular"));
        //调用service方法查询所有MV
        List<Cd> cdList = cdService.queryPopular(popular);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("cdList", cdList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/cd_list.jsp").forward(request, response);
    }

    //根据年代查询所有歌MV并显示
    public void queryCyear(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取需要根据的条件--年代
        Integer cyear = Integer.valueOf(request.getParameter("cyear"));
        //调用service方法查询所有MV
        List<Cd> cdList = cdService.queryCyear(cyear);
        //将查到的数据存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("cdList", cdList);
        //跳到MV展示页面
        request.getRequestDispatcher("index/cd_list.jsp").forward(request, response);
    }

    //点击播放或者下载，歌曲的下载次数和播放次数都加一
    //1.播放
    public void updateSongPlay(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //获取要播放的歌曲id
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        Song song = new Song(sid);
        //调用service方法进行更改
        int row = songService.updateSongByPlay(song);
        if (row > 0) {
            response.getWriter().write(JSON.toJSONString(200));
        }
    }

    //2.下载
    public void updateSongDown(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        //获取要下载的歌曲名
        String songName = request.getParameter("songName");
        String songUrl = request.getParameter("songUrl");
        Song song = new Song(songName);
        //指定要下载文件的路径
        String path = "E:\\bishe\\songurl\\";
        //查询文件
        File file = new File(path + songUrl);
        //判断文件是否存在
        if (file.exists()) {
            //response.setContentType("application/x-msdownload"); //设置响应类型,此处为下载类型
            //response.setHeader("Content-Disposition", "attachment;songUrl="+java.net.URLEncoder.encode(songUrl, "UTF-8"));//以附件的形式打开
            //创建一个字节输入流
            InputStream is = new FileInputStream(file);
            //得到一个输出流
            //ServletOutputStream os=response.getOutputStream();
            String newpath = "E:\\bishe\\songdown\\" + songUrl;
            File file1 = new File(newpath);
            if (!file1.exists()) {
                FileOutputStream fs = new FileOutputStream(newpath);
                byte[] bb = new byte[1024 * 1024];
                int len = 0;
                while ((len = is.read(bb)) != -1) {
                    fs.write(bb, 0, len);
                }
                is.close();
                fs.close();
                //调用service方法进行更改
                songService.updateSongByDown(song);
                response.getWriter().write(JSON.toJSONString(200));
            } else {
                if(file1.exists()){
                    response.getWriter().write(JSON.toJSONString(202));
                }
            }
        } else {
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //获取下载好的数据
    public void queryAllDownLoad(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        List<Song> songDown = songService.queryAllDownLoad();
        HttpSession session = request.getSession();
        session.setAttribute("songDown", songDown);
        for (Song song : songDown) {
            //遍历集合获取歌曲id
            Integer cdId = song.getCdId();
            //调用service方法查询cd
            Cd cd = cdService.findCdById(cdId);
            song.setCd(cd);
        }
        if (songDown != null) {
            request.getRequestDispatcher("index/my_music.jsp").forward(request, response);
        }
    }
}
