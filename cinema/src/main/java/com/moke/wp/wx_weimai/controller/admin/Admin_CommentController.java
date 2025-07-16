package com.moke.wp.wx_weimai.controller.admin;

import com.moke.wp.wx_weimai.config.util.PageBean;
import com.moke.wp.wx_weimai.config.util.Result;
import com.moke.wp.wx_weimai.entity.Comment;
import com.moke.wp.wx_weimai.entity.Vo.AdminComment;
import com.moke.wp.wx_weimai.mapper.CommentMapper;
import com.moke.wp.wx_weimai.service.CommentSerice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/comment")
@RequiresPermissions("评论管理")
public class Admin_CommentController {

    @Autowired
    private CommentSerice commentSerice;
    @Autowired
    private CommentMapper commentMapper;

    //获取评论列表
    @GetMapping("/getComments")
    public Result getComments(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("limit") Integer limit,
                              @RequestParam(value = "keyword",required = false) String keyword){
        PageBean<AdminComment> adminCommentPageBean = commentSerice.getComments(pageNum,limit,keyword);
        return new Result(adminCommentPageBean);
    }

    //删除评论
    @PostMapping("/deleteComment")
    public Result deleteComment(@RequestBody HashMap<String,Integer> map){
        Integer commentId = map.get("commentId");
        commentSerice.deleteById(commentId);
        return new Result(commentId);
    }

    //删除评论
    @GetMapping("/selectRank")
    public Result selectRank(){
        List<Comment> result = commentMapper.selectRank();
        return new Result(result);
    }

}
