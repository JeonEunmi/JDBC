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

// ���� �޴��� �׼� Ŭ����
public class EmpService {

	private String id;
	private LoginDAO loginDAO = new LoginDAO();
	private RegionDAO regionDAO = new RegionDAO();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private JobDAO jobDAO = new JobDAO();
	private EmpDAO empDAO = new EmpDAO();

	public void login(Scanner sc) {
		/*
		 * ���̵�>admin �н�����>1111 ������ 'admin' �α��εǾ����ϴ�.
		 */

		System.out.print("���̵�> ");
		String id = sc.nextLine();

		System.out.print("�н�����> ");
		String pw = sc.nextLine();

		int result = this.loginDAO.login(new Login(id, pw));

		if (result == 1) {
			this.id = id;
			System.out.printf("������ '%s' �α��εǾ����ϴ�.", id);
			this.main(sc);
		} else {
			System.out.println("���̵� �Ǵ� �н����尡 Ʋ�Ƚ��ϴ�.");
		}

	}

	// 1.�������� 2.������������ 3.�������������� 0. �α׾ƿ�
	private void main(Scanner sc) {

		/*
		 * -------------- �������� v2.0 (������:admin) -------------- 1.�������� 2.������������ 3.��������������
		 * ����>
		 */

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� v2.0 (������:%s)%n", this.id);
			System.out.println("--------------");
			System.out.println("1.��������  2.������������  3.��������������");
			System.out.print("����(����: 0)>");

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
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}
	}

	// -----------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ����
	// 1.�����Է� 2.������ü��� 3.�����˻� 4.��������
	private void menu1(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� v2.0 (������:%s) > ���� ���� %n", this.id);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.������ü���  3.�����˻�  4.��������");
			System.out.print("����(����: 0)> ");

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
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > ���� �Է�
	private void menu1_sub1(Scanner sc) {

		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� �Է�%n", id);
		System.out.println("--------------");

		System.out.print("�̸�> ");
		String name = sc.nextLine();

		String regExp1 = "\\d{6}-\\d{7}";

		while (true) {

			String hireDate = null;

			System.out.print("�ֹι�ȣ(YYMMDD-NNNNNNN)> ");
			String ssn = sc.nextLine();

			if (Pattern.matches(regExp1, ssn) == false) {
				System.out.println();
				System.out.println("���ֹι�ȣ ���Ŀ� �°� �Է��ϼ���.");

			} else {

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				while (true) {
					sf.setLenient(false);

					System.out.print("�Ի���(YYYY-MM-DD)> ");
					hireDate = sc.nextLine();

					try {
						sf.parse(hireDate);
						break;
					} catch (Exception e1) {
						// Ʋ�� ��¥�� ��쿹�ܹ߻�
						System.out.println();
						System.out.println("����¥ ���Ŀ� �°� �Է��ϼ���(yyyy-MM-dd) \n");
					}
				}

				while (true) {

					String regExp2 = "(010)-\\d{3,4}-\\d{4}";

					System.out.print("��ȭ��ȣ(010-XXXX-XXXX)> ");
					String phone = sc.nextLine();

					if (Pattern.matches(regExp2, phone) == false) {
						System.out.println();
						System.out.println("����ó ������ �����ּ���. (010-xxxx-xxxx)");
					} else {

						System.out.println("--------------");
						System.out.println("������ȣ / ������");

						List<Regions> r = this.regionDAO.list1();

						for (Regions region : r) {
							System.out.println(region.print1());
						}
						System.out.println("--------------");

						while (true) {

							String regExp3 = "(REG)\\d{2}";

							System.out.print("������ȣ> ");
							String regId = sc.nextLine();

							for (Regions reg : r) {

								if (Pattern.matches(regExp3, regId) == false) {
									System.out.println();
									System.out.println("������ȣ ������ �ƴմϴ�.");
								} else if (regId.equals(reg.getregId())){

									System.out.println("--------------");
									System.out.println("�μ���ȣ / �μ���");

									List<Departments> d = this.departmentDAO.list();

									for (Departments dep : d) {
										System.out.println(dep);
									}

									System.out.println("--------------");

									while (true) {

										String regExp4 = "(DEPT)\\d{2}";

										System.out.print("�μ���ȣ> ");
										String deptId = sc.nextLine();

										for (Departments depart : d) {

											if (Pattern.matches(regExp4, deptId) == false
													|| deptId != depart.getDepId()) {
												System.out.println();
												System.out.println("�μ���ȣ ������ �ƴմϴ�.");
											} else {

												System.out.println("--------------");
												System.out.println("������ȣ / ������ / �ּұ⺻��");

												List<Jobs> j = this.jobDAO.list();

												for (Jobs job : j) {
													System.out.println(job);
												}

												System.out.println("--------------");

												while (true) {

													String regExp5 = "(JOB)\\d{2}";

													System.out.print("������ȣ> ");
													String jobId = sc.nextLine();

													for (Jobs jobs : j) {
														if (Pattern.matches(regExp5, jobId) == false
																|| jobId != jobs.getJobId()) {
															System.out.println();
															System.out.println("������ȣ ������ �ƴմϴ�.");
														} else {
															System.out.print("�⺻��> ");
															int basicPay = sc.nextInt();
															sc.nextLine();
															System.out.print("����> ");
															int extraPay = sc.nextInt();
															sc.nextLine();

															int pay = basicPay + extraPay;

															int result = this.empDAO.employeeAdd(new Employees(null,
																	name, ssn, hireDate, phone, regId, deptId, jobId,
																	basicPay, extraPay, pay));

															System.out.printf("%s ���� �Է��� �Ϸ�Ǿ����ϴ�.", result);

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

	// ���� ���� v2.0 (������:admin) > ���� ���� > ������ü���
	private void menu1_sub2(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ���%n", id);
		System.out.println("--------------");
		System.out.println("1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������");
		System.out.print("����> ");

		int key = sc.nextInt();
		sc.nextLine();

		String tmp = null;

		if (key == 1) {
			tmp = "���";
		} else if (key == 2) {
			tmp = "�̸�";
		} else if (key == 3) {
			tmp = "������";
		} else if (key == 4) {
			tmp = "�μ���";
		} else if (key == 5) {
			tmp = "������";
		}

		List<Employees> emp = this.empDAO.list1(key);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > %s ����%n", id, tmp);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");
		
		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > �����˻�
	private void menu1_sub3(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� �˻�%n", id);
		System.out.println("--------------");
		System.out.println("1.����˻�  2.�̸��˻�  3.������˻�  4.�μ���˻�  5.������˻�");
		System.out.print("����> ");

		int key = sc.nextInt();
		sc.nextLine();

		System.out.print("�˻��ܾ�> ");
		String value = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(key, value);
		System.out.println();
		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > ��������
	private void menu1_sub4(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ����%n", id);
		System.out.println("--------------");
		System.out.print("�������̵�(EMPXXX)> ");
		String eid = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(1, eid);

		if (emp.size() != 0) {
			System.out.println();
			System.out.println("--------------");
			System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");
			
			
			for (Employees e : emp) {
				System.out.println(e);
			}
			
			System.out.println();
			
			System.out.println("���� �����Ͻðڽ��ϱ�?(0/1) ");

			int num = sc.nextInt();
			sc.nextLine();

			if (num == 1) {
				int result = this.empDAO.empDelete(eid);

				System.out.println("*");
				System.out.printf("%s���� ������ ������ �Ϸ�Ǿ����ϴ�.! %n", result);

			} else if (num == 0) {
				System.out.println("������ ��ҵǾ����ϴ�.");
			} else {
				System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�!");
			}

		}else {
			System.out.println("�˻������ �����ϴ�.");
		}

		System.out.println();

	}

	// --------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ���� ����
	// 1.�������� 2.�μ����� 3.��������
	private void menu2(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ����%n", id);
			System.out.println("--------------");
			System.out.println("1.��������  2.�μ�����  3.��������");
			System.out.print("����(����: 0)> ");

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
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	
	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > ��������
	private void menu2_sub1(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > ��������%n", id);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.�������  3.��������");
			System.out.print("����(����: 0)> ");

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
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}
		
	}
	
	private void menu2_sub1_sub1(Scanner sc) {
		
		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� �Է�%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������");
		
		List<Regions> region = this.regionDAO.list1();
		
		for(Regions r : region) {
			System.out.println(r.print1());
		}
		
		System.out.print("�ű� ���� �̸�> ");
		String regName = sc.nextLine();
		
		int result = this.regionDAO.regionAdd(new Regions(null, regName));
		
		System.out.printf("%s���� �ű� ���� ���� �Է� �Ϸ�!", result);
		
	}
	
	private void menu2_sub1_sub2() {
		
	}
	
	private void menu2_sub1_sub3(Scanner sc) {
		
	}

	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > �μ�����
	private void menu2_sub2(Scanner sc) {

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > ��������
	private void menu2_sub3(Scanner sc) {

	}

	// --------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ��������������
	// 1.�������߰� 2.�н����庯��
	private void menu3() {

	}

	// ���� ���� v2.0 (������:admin) > �������������� > �������߰�
	private void menu3_sub1() {

	}

	// ���� ���� v2.0 (������:admin) > �������������� > �н����庯��
	private void menu3_sub2() {

	}

}
