/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.smhdemo.common.report.entity.view;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 布尔视图
 * 
 * <ul>
 * </ul>
 * 
 * @author zhoudongchu
 */
@Entity
@PrimaryKeyJoinColumn(name = "view_id")
public class BooleanView extends ComponentView {

    private static final long serialVersionUID = 3751586581957193886L;
}
