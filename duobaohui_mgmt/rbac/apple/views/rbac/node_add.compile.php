<?php
/**
* FEROS™ PHP template engine
* @author feros<admin@feros.com.cn>
* @copyright ©2016 feros.com.cn
* @link http://www.feros.com.cn
* @version 2.0.2
*/
?><h3 class="smaller lighter blue">添加节点</h3><form class="form-horizontal" id="validation-form" method="post" novalidate="novalidate" action="<?php echo $fromDomain;?>node_add"><?php if(isset($parent)):?><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="pid">父节点:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><input  type="text" value="<?php echo $parent->name; ?>" disabled/><input name="pid"  type="hidden" value="<?php echo $parent->node_id; ?>"/></div></div></div><?php endif; ?><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">节点名称:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><input type="text" id="name" name="name" class="col-xs-12 col-sm-5" value=""></div></div></div><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="action">action:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><input type="text" id="action" name="action" class="col-xs-12 col-sm-5" value=""></div></div></div><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sort">排序:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><input type="text" id="sort" name="sort" class="col-xs-12 col-sm-5" value="10"></div></div></div><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="status">状态:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><select name="status" class=""><option value="0">开启</option><option value="1" >禁用</option></select></div></div></div><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="type">类型:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><select name="type" class="required combox"><option value="0" >目录</option><option value="1" >页面</option><option value="2" >模块</option></select></div></div></div><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="roleIds">授权:</label><div class="col-xs-12 col-sm-9"><div class="clearfix"><?php foreach($roleList as $v):  if($v->role_id!=1): /* 管理员*/ ?><input type="checkbox" name="roleIds[]" value="<?php echo $v->role_id; ?>" /><?php echo $v->name;  endif;  endforeach; ?></div></div></div><div class="clearfix form-actions"><div class="col-md-offset-3 col-md-9"><button class="btn btn-info" type="button" id="form_submit"><i class="icon-ok bigger-110"></i>
					保存
				</button></div></div></form><div class="space-4"></div><script>	var vform = {
		id: '#validation-form',
		submitBtn: '#form_submit'
	};
</script><?php echo $this->fetch('block/common');?>
