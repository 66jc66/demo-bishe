package com.xyxy.servlet;

import com.alibaba.fastjson.JSON;
import com.xyxy.pojo.ResultInfo;
import com.xyxy.pojo.Singer;
import com.xyxy.pojo.Song;
import com.xyxy.service.SongService;
import com.xyxy.util.PageUtil;
import com.xyxy.util.UploadUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

@WebServlet("/song")
@MultipartConfig
public class SongServlet extends BaseServlet {
    private SongService songService = new SongService();
    private ResultInfo resultInfo=new ResultInfo();

    //增加歌曲
    public void insertSong(HttpServletRequest request,
                           HttpServletResponse response) {
        try {
            //获取添加页面的数据
            String songName = request.getParameter("songName");
            String singerName = request.getParameter("singerName");
            String cdidStr = request.getParameter("cdId");
            Integer cdId=Integer.valueOf(cdidStr);
            String language = request.getParameter("language");
            String geci = request.getParameter("geci");
            String faDate = request.getParameter("faDate");
            String songTime =request.getParameter("songTime");
            //设置目标服务器路径
            String path = "E:\\bishe\\songurl\\";
            //创建上传的文件对象：Part里面封装了请求中上传的文件参数信息
            Part part = request.getPart("songUrl");
            //获取上传的文件名称:
            String fileName = part.getSubmittedFileName();
            //将要上传到的路径名组合起来
            String songUrl1 = path + fileName;
            String songUrl=fileName;
            //将文件上传到目标服务器:目前文件的路径及文件名称
            part.write(songUrl1);
            Song song = new Song(songName, singerName, cdId, language,geci, faDate, songUrl, songTime);
            int row = songService.insertSong(song);
            if(row>0){
                //增加成功继续添加
                response.sendRedirect("layuimini/page/song_add.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据歌曲名来查询是否有重复的歌曲
    public void checkSongName(HttpServletRequest request,
                              HttpServletResponse response) throws Exception{
        //获取歌名
        String songName = request.getParameter("songName");
        //调用service方法来查询
        Song song=songService.checkBySongName(songName);
        //判断song是否为空
        if(song==null){
            //如果为空就传一个可以执行的值ok
            response.getWriter().write(JSON.toJSONString("ok"));
        }else {
            //如果不为空就传递一个fail
            response.getWriter().write(JSON.toJSONString("fail"));
        }
    }

    //查询所有的歌曲
    public void querySongAll(HttpServletRequest request,
                             HttpServletResponse response) throws Exception{
        //获取分页的第几页，以及每页显示多少数据
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询数据库表中的所有数据
        Integer count = songService.queryCount();
        //调用工具类来进行分页
        PageUtil<Song> pageUtil = new PageUtil<Song>(count, page, limit);
        List<Song> songsList = songService.queryUserByPage(pageUtil);
        //设置返回结果信息
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(songsList);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }

    //根据id查询歌曲
    public void findById(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //获取输入框的值
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        //调用service方法查询对象
        Song songs = songService.checkById(sid);
        //查询到对象存入作用域中
        HttpSession session = request.getSession();
        session.setAttribute("songs", songs);
        request.getRequestDispatcher("layuimini/page/song_edit.jsp").forward(request, response);
    }

    //修改歌曲信息
    public void updateSong(HttpServletRequest request,
                           HttpServletResponse response) throws Exception{
        //获取修改页面的数据
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        String songName = request.getParameter("songName");
        String singerName = request.getParameter("singerName");
        Integer cdId = Integer.valueOf(request.getParameter("cdId"));
        String language = request.getParameter("language");
        String geci = request.getParameter("geci");
        String faDate = request.getParameter("faDate");
        String  songTime =request.getParameter("songTime");
        Integer downloadCount = Integer.valueOf(request.getParameter("downloadCount"));
        Integer playCount = Integer.valueOf(request.getParameter("playCount"));
        Integer discussNum = Integer.valueOf(request.getParameter("discussNum"));
        //上传音频
        Part part = request.getPart("songUrl");
        long len = part.getSize();
        if (len == 0) {//判断是否上传了文件
            //将歌曲参数封装成对象
            Song song = new Song( sid,  discussNum, songName,singerName, cdId,  playCount,  downloadCount,  language, geci, faDate,songTime);
            int row = songService.updateSong(song);
        } else {
            String songUrl = UploadUtil.uploadFile(part);
            //将歌曲参数封装成对象
            Song song = new Song( sid,discussNum,  singerName,  cdId,  playCount,  downloadCount,  songName,  language, geci, faDate,  songUrl,  songTime);
            int row =  songService.updateSong(song);
        }
    }

    //根据id来删除歌曲信息
    public void deleteById(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取单个id值
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        int row = songService.deleteById(sid);
        if (row > 0) {
            //删除成功返回200
            response.getWriter().write(JSON.toJSONString(200));
        } else {
            //删除失败返回201
            response.getWriter().write(JSON.toJSONString(201));
        }
    }

    //批量删除歌曲信息
    public void batchDelete(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //获取到勾选的字符串组合
        String ids = request.getParameter("ids");
        int row = songService.deleteByIds(ids);
        if (row > 0) {
            //删除成功返回200
            response.getWriter().write(JSON.toJSONString("200"));
        } else {
            //删除失败返回201
            response.getWriter().write(JSON.toJSONString("201"));
        }
    }

    //根据歌曲名，歌手名，专辑id查询相关用户
    public void selectBySome(HttpServletRequest request,
                             HttpServletResponse response) throws Exception{
        //获取搜索框中的值
        String songName = request.getParameter("songName");
        String singerName = request.getParameter("singerName");
        String cidStr = request.getParameter("cdId");
        //获取页面中要分页的数量以及一页中的数量
        Integer limit = Integer.valueOf(request.getParameter("limit"));
        String page = request.getParameter("page");
        //查询所有的歌曲数量
        Integer count = songService.queryCount1(songName,singerName,cidStr);
        PageUtil<Singer> pageUtil = new PageUtil<Singer>(count, page, limit);
        List<Song> songs=songService.selectBySome(songName,singerName,cidStr,pageUtil);
        //通过resultInfo来对json数据进行设计
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setResult(songs);
        response.getWriter().write(JSON.toJSONString(resultInfo));
    }
}


