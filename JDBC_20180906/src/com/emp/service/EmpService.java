package com.emp.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.emp.domain.Departments;
import com.emp.domain.Employees;
import com.emp.domain.Jobs;
import com.emp.domain.Login;
import com.emp.domain.Regions;
import com.emp.persistance.DepartmentDAO;
import com.emp.persistance.EmpDAO;
import com.emp.persistance.JobDAO;
import com.emp.persistance.LoginDAO;
import com.emp.persistance.RegionDAO;

// 메인 메뉴별 액션 클래스
public class EmpService {

	private String id;
	private LoginDAO loginDAO = new LoginDAO();
	private RegionDAO regionDAO = new RegionDAO();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private JobDAO jobDAO = new JobDAO();
	private EmpDAO empDAO = new EmpDAO();

	public void login(Scanner sc) {
		/*
		 * 아이디>admin 패스워드>1111 관리자 'admin' 로그인되었습니다.
		 */

		System.out.print("아이디> ");
		String id = sc.nextLine();

		System.out.print("패스워드> ");
		String pw = sc.nextLine();

		int result = this.loginDAO.login(new Login(id, pw));

		if (result == 1) {
			this.id = id;
			System.out.printf("관리자 '%s' 로그인되었습니다.", id);
			this.main(sc);
		} else {
			System.out.println("아이디 또는 패스워드가 틀렸습니다.");
		}

	}

	// 1.직원관리 2.기초정보관리 3.관리자정보관리 0. 로그아웃
	private void main(Scanner sc) {

		/*
		 * -------------- 직원관리 v2.0 (관리자:admin) -------------- 1.직원관리 2.기초정보관리 3.관리자정보관리
		 * 선택>
		 */

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 v2.0 (관리자:%s)%n", this.id);
			System.out.println("--------------");
			System.out.println("1.직원관리  2.기초정보관리  3.관리자정보관리");
			System.out.print("선택(종료: 0)>");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu1(sc);
				break;
			case 2:
				this.menu2(sc);
				break;
			case 3:
				this.menu3();
				break;
			default:
				System.out.println("잘못된 접근입니다.");
				break;
			}
		}
	}

	// -----------------------------------------------------------------------------

	// 직원 관리 v2.0 (관리자:admin) > 직원 관리
	// 1.직원입력 2.직원전체출력 3.직원검색 4.직원삭제
	private void menu1(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원관리 v2.0 (관리자:%s) > 직원 관리 %n", this.id);
			System.out.println("--------------");
			System.out.println("1.직원입력  2.직원전체출력  3.직원검색  4.직원삭제");
			System.out.print("선택(종료: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu1_sub1(sc);
				break;
			case 2:
				this.menu1_sub2(sc);
				break;
			case 3:
				this.menu1_sub3(sc);
				break;
			case 4:
				this.menu1_sub4(sc);
				break;
			default:
				System.out.println("잘못된 접근입니다.");
				break;
			}
		}

	}

	// 직원 관리 v2.0 (관리자:admin) > 직원 관리 > 직원 입력
	private void menu1_sub1(Scanner sc) {

		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원 관리 v2.0 (관리자:%s) > 직원 관리 > 직원 입력%n", id);
		System.out.println("--------------");

		System.out.print("이름> ");
		String name = sc.nextLine();

		String regExp1 = "\\d{6}-\\d{7}";

		while (true) {

			String hireDate = null;

			System.out.print("주민번호(YYMMDD-NNNNNNN)> ");
			String ssn = sc.nextLine();

			if (Pattern.matches(regExp1, ssn) == false) {
				System.out.println();
				System.out.println("▶주민번호 형식에 맞게 입력하세요.");

			} else {

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				while (true) {
					sf.setLenient(false);

					System.out.print("입사일(YYYY-MM-DD)> ");
					hireDate = sc.nextLine();

					try {
						sf.parse(hireDate);
						break;
					} catch (Exception e1) {
						// 틀린 날짜인 경우예외발생
						System.out.println();
						System.out.println("▶날짜 형식에 맞게 입력하세요(yyyy-MM-dd) \n");
					}
				}

				while (true) {

					String regExp2 = "(010)-\\d{3,4}-\\d{4}";

					System.out.print("전화번호(010-XXXX-XXXX)> ");
					String phone = sc.nextLine();

					if (Pattern.matches(regExp2, phone) == false) {
						System.out.println();
						System.out.println("연락처 형식을 맞춰주세요. (010-xxxx-xxxx)");
					} else {

						System.out.println("--------------");
						System.out.println("지역번호 / 지역명");

						List<Regions> r = this.regionDAO.list1();

						for (Regions region : r) {
							System.out.println(region.print1());
						}
						System.out.println("--------------");

						while (true) {

							String regExp3 = "(REG)\\d{2}";

							System.out.print("지역번호> ");
							String regId = sc.nextLine();

							for (Regions reg : r) {

								if (Pattern.matches(regExp3, regId) == false) {
									System.out.println();
									System.out.println("지역번호 범위가 아닙니다.");
								} else if (regId.equals(reg.getregId())){

									System.out.println("--------------");
									System.out.println("부서번호 / 부서명");

									List<Departments> d = this.departmentDAO.list();

									for (Departments dep : d) {
										System.out.println(dep);
									}

									System.out.println("--------------");

									while (true) {

										String regExp4 = "(DEPT)\\d{2}";

										System.out.print("부서번호> ");
										String deptId = sc.nextLine();

										for (Departments depart : d) {

											if (Pattern.matches(regExp4, deptId) == false
													|| deptId != depart.getDepId()) {
												System.out.println();
												System.out.println("부서번호 범위가 아닙니다.");
											} else {

												System.out.println("--------------");
												System.out.println("직위번호 / 직위명 / 최소기본급");

												List<Jobs> j = this.jobDAO.list();

												for (Jobs job : j) {
													System.out.println(job);
												}

												System.out.println("--------------");

												while (true) {

													String regExp5 = "(JOB)\\d{2}";

													System.out.print("직위번호> ");
													String jobId = sc.nextLine();

													for (Jobs jobs : j) {
														if (Pattern.matches(regExp5, jobId) == false
																|| jobId != jobs.getJobId()) {
															System.out.println();
															System.out.println("직위번호 범위가 아닙니다.");
														} else {
															System.out.print("기본급> ");
															int basicPay = sc.nextInt();
															sc.nextLine();
															System.out.print("수당> ");
															int extraPay = sc.nextInt();
															sc.nextLine();

															int pay = basicPay + extraPay;

															int result = this.empDAO.employeeAdd(new Employees(null,
																	name, ssn, hireDate, phone, regId, deptId, jobId,
																	basicPay, extraPay, pay));

															System.out.printf("%s 건의 입력이 완료되었습니다.", result);

														}
													}

												}
											}

										}

									}

								}
							}
						}

					}
				}

			}
		}

	}

	// 직원 관리 v2.0 (관리자:admin) > 직원 관리 > 직원전체출력
	private void menu1_sub2(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("직원 관리 v2.0 (관리자:%s) > 직원 관리 > 직원 전체 출력%n", id);
		System.out.println("--------------");
		System.out.println("1.사번정렬  2.이름정렬  3.지역명정렬  4.부서명정렬  5.직위명정렬");
		System.out.print("선택> ");

		int key = sc.nextInt();
		sc.nextLine();

		String tmp = null;

		if (key == 1) {
			tmp = "사번";
		} else if (key == 2) {
			tmp = "이름";
		} else if (key == 3) {
			tmp = "지역명";
		} else if (key == 4) {
			tmp = "부서명";
		} else if (key == 5) {
			tmp = "직위명";
		}

		List<Employees> emp = this.empDAO.list1(key);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("직원 관리 v2.0 (관리자:%s) > 직원 관리 > 직원 전체 출력 > %s 정렬%n", id, tmp);

		System.out.println("--------------");
		System.out.printf("전체 인원 : %s명%n", emp.size());
		System.out.println("--------------");
		System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");
		
		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// 직원 관리 v2.0 (관리자:admin) > 직원 관리 > 직원검색
	private void menu1_sub3(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("직원 관리 v2.0 (관리자:%s) > 직원 관리 > 직원 검색%n", id);
		System.out.println("--------------");
		System.out.println("1.사번검색  2.이름검색  3.지역명검색  4.부서명검색  5.직위명검색");
		System.out.print("선택> ");

		int key = sc.nextInt();
		sc.nextLine();

		System.out.print("검색단어> ");
		String value = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(key, value);
		System.out.println();
		System.out.println("--------------");
		System.out.printf("전체 인원 : %s명%n", emp.size());
		System.out.println("--------------");
		System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// 직원 관리 v2.0 (관리자:admin) > 직원 관리 > 직원삭제
	private void menu1_sub4(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("직원 관리 v2.0 (관리자:%s) > 직원 관리 > 직원 삭제%n", id);
		System.out.println("--------------");
		System.out.print("직원아이디(EMPXXX)> ");
		String eid = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(1, eid);

		if (emp.size() != 0) {
			System.out.println();
			System.out.println("--------------");
			System.out.println("사번 / 이름 / 주민번호 / 입사일 / 전화번호 / 지역명 / 부서명 / 직위명 / 기본급 / 수당 / 급여");
			
			
			for (Employees e : emp) {
				System.out.println(e);
			}
			
			System.out.println();
			
			System.out.println("정말 삭제하시겠습니까?(0/1) ");

			int num = sc.nextInt();
			sc.nextLine();

			if (num == 1) {
				int result = this.empDAO.empDelete(eid);

				System.out.println("*");
				System.out.printf("%s명의 직원이 삭제가 완료되었습니다.! %n", result);

			} else if (num == 0) {
				System.out.println("삭제가 취소되었습니다.");
			} else {
				System.out.println("잘못된 번호를 입력하셨습니다!");
			}

		}else {
			System.out.println("검색결과가 없습니다.");
		}

		System.out.println();

	}

	// --------------------------------------------------------------------------

	// 직원 관리 v2.0 (관리자:admin) > 기초 정보 관리
	// 1.지역관리 2.부서관리 3.직위관리
	private void menu2(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원 관리 v2.0 (관리자:%s) > 기초 정보 관리%n", id);
			System.out.println("--------------");
			System.out.println("1.지역관리  2.부서관리  3.직위관리");
			System.out.print("선택(종료: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub1(sc);
				break;
			case 2:
				this.menu2_sub2(sc);
				break;
			case 3:
				this.menu2_sub3(sc);
				break;
			case 4:
			default:
				System.out.println("잘못된 접근입니다.");
				break;
			}
		}

	}

	
	// 직원 관리 v2.0 (관리자:admin) > 기초 정보 관리 > 지역관리
	private void menu2_sub1(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("직원 관리 v2.0 (관리자:%s) > 기초 정보 관리 > 지역관리%n", id);
			System.out.println("--------------");
			System.out.println("1.지역입력  2.지역출력  3.지역삭제");
			System.out.print("선택(종료: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub1_sub1(sc);
				break;
			case 2:
				this.menu2_sub1_sub2();
				break;
			case 3:
				this.menu2_sub1_sub3(sc);
				break;
			case 4:
			default:
				System.out.println("잘못된 접근입니다.");
				break;
			}
		}
		
	}
	
	private void menu2_sub1_sub1(Scanner sc) {
		
		System.out.println();
		System.out.println("--------------");
		System.out.printf("직원 관리 v2.0 (관리자:%s) > 기초 정보 관리 > 지역관리 > 지역 입력%n", id);
		System.out.println("--------------");
		System.out.println("지역번호 / 지역명");
		
		List<Regions> region = this.regionDAO.list1();
		
		for(Regions r : region) {
			System.out.println(r.print1());
		}
		
		System.out.print("신규 지역 이름> ");
		String regName = sc.nextLine();
		
		int result = this.regionDAO.regionAdd(new Regions(null, regName));
		
		System.out.printf("%s건의 신규 지역 정보 입력 완료!", result);
		
	}
	
	private void menu2_sub1_sub2() {
		
	}
	
	private void menu2_sub1_sub3(Scanner sc) {
		
	}

	// 직원 관리 v2.0 (관리자:admin) > 기초 정보 관리 > 부서관리
	private void menu2_sub2(Scanner sc) {

	}

	// 직원 관리 v2.0 (관리자:admin) > 기초 정보 관리 > 직위관리
	private void menu2_sub3(Scanner sc) {

	}

	// --------------------------------------------------------------------------

	// 직원 관리 v2.0 (관리자:admin) > 관리자정보관리
	// 1.관리자추가 2.패스워드변경
	private void menu3() {

	}

	// 직원 관리 v2.0 (관리자:admin) > 관리자정보관리 > 관리자추가
	private void menu3_sub1() {

	}

	// 직원 관리 v2.0 (관리자:admin) > 관리자정보관리 > 패스워드변경
	private void menu3_sub2() {

	}

}
