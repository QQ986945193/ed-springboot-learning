package com.david.domain;
/**
 * #### 常用注解

> @Null	只能是null   
  @NotNull 不能为null 注意用在基本类型上无效，基本类型有默认初始值   
  @AssertFalse 必须为false   
  @AssertTrue	必须是true   
  
> 字符串/数组/集合检查：(字符串本身就是个数组)   
  @Pattern(regexp="reg") 验证字符串满足正则   
  @Size(max, min) 验证字符串、数组、集合长度范围   
  @NotEmpty 验证字符串不为空或者null   
  @NotBlank 验证字符串不为null或者trim()后不为空   
  
> 数值检查：同时能验证一个字符串是否是满足限制的数字的字符串   
  @Max 规定值得上限int   
  @Min 规定值得下限   	
  @DecimalMax("10.8")	以传入字符串构建一个BigDecimal，规定值要小于这个值    
  @DecimalMin 可以用来限制浮点数大小   
  @Digits(int1, int2) 限制一个小数，整数精度小于int1；小数部分精度小于int2   
  @Digits 无参数，验证字符串是否合法   	
  @Range(min=long1,max=long2) 检查数字是否在范围之间   
  这些都包括边界值   
  
> 日期检查：Date/Calendar   
  @Past 限定一个日期，日期必须是过去的日期   
  @Future 限定一个日期，日期必须是未来的日期   
  
> 其他验证：   
  @Vaild 递归验证，用于对象、数组和集合，会对对象的元素、数组的元素进行一一校验   
  @Email 用于验证一个字符串是否是一个合法的右键地址，空字符串或null算验证通过   
  @URL(protocol=,host=,port=,regexp=,flags=) 用于校验一个字符串是否是合法URL   

#### 注意

@Validated 和 BindingResult 是一一对应的，如果有多个@Validated，那么每个@Validated后面跟着的BindingResult就是这个@Validated的验证结果，顺序不能乱，比如
    
````
@RequestMapping("/saveUser")
public ModelAndView saveUser(@Validated User user, BindingResult userResult, @Validated Other other, BindingResult otherResult, Model model) {
    ......
}
````
userResult对应user实体类的校验结果，otherResult对应other类的校验结果
#### 如下异常
```
HV000030: No validator could be found for constraint 'javax.validation.constraints.Pattern' validating type 'java.lang.Integer'. Check configuration for 'phone'
```
定义属性时将phone类型设置为Integer了， @Pattern只能作用于String类型的属性，所以报上面的错。    


 * @author ：David
 * @weibo ：http://weibo.com/mcxiaobing
 * @github: https://github.com/QQ986945193
 */

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

	@NotEmpty(message = "用户名不能为空")
	@Length(max = 10, min = 4, message = "用户名长度4-10")
	private String username;

	private Integer gender;

	@Max(value = 99, message = "年龄超出上限")
	@Min(value = 1, message = "年龄超出下限")
	private Integer age;

	@Email(message = "默认的邮箱验证@Email不通过")
	private String email;

	@Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", message = "URL格式不正确")
	private String url;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", gender=" + gender + ", age=" + age + ","+"email=" + email + ", url=" + url + "]";
	}

}
