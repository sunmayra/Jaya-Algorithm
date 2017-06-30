



public final class ShiftRotation {
	private static double[] rotatefunc(double[] x, int nx, double[] Mr) {
		double[] xrot = new double[nx];
		int i;
		int j;

		for (i = 0; i < nx; i++) {
			xrot[i] = 0;

			for (j = 0; j < nx; j++) {
				xrot[i] = xrot[i] + x[j] * Mr[i * nx + j];
			}
		}

		return xrot;
	}

	private static double[] shiftfunc(double[] x, int nx, double[] Os) {
		double[] xshift = new double[nx];
		int i;

		for (i = 0; i < nx; i++) {
			xshift[i] = x[i] - Os[i];
		}

		return xshift;
	}

	public final static double[] sr_func(double[] x, int nx, double[] Os, double[] Mr, double sh_rate, int s_flag,
			int r_flag) {
		double[] sr_x;
		double[] y;
		int i;
		int j;

		if (s_flag == 1) {
			if (r_flag == 1) {
				y = shiftfunc(x, nx, Os);

				for (i = 0; i < nx; i++) {
					y[i] = y[i] * sh_rate;
				}

				sr_x = rotatefunc(y, nx, Mr);
			} else {
				sr_x = shiftfunc(x, nx, Os);

				for (i = 0; i < nx; i++) {
					sr_x[i] = sr_x[i] * sh_rate;
				}
			}
		} else {
			if (r_flag == 1) {
				y = new double[nx];

				for (i = 0; i < nx; i++) {
					y[i] = x[i] * sh_rate;
				}

				sr_x = rotatefunc(y, nx, Mr);
			} else {
				sr_x = new double[nx];

				for (j = 0; j < nx; j++) {
					sr_x[j] = x[j] * sh_rate;
				}
			}
		}

		return sr_x;
	}
}


