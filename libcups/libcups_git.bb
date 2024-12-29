SUMMARY = "CUPS v3 Libraries"
HOMEPAGE = "https://openprinting.github.io/cups/cups3.html"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "avahi openssl zlib"
RDEPENDS_${PN} = "avahi-daemon openssl libavahi-client zlib"

SRC_URI = " \
	gitsm://git@github.com/OpenPrinting/libcups.git;protocol=ssh;branch=master \
"
PV = "3.0+git${SRCPV}"
SRCREV = "4cef27ac8d94e7fa31bafd316a5bb7f66a980fc7"

S = "${WORKDIR}/git"

inherit autotools-brokensep binconfig pkgconfig

CLEANBROKEN = "1"
EXTRA_AUTORECONF += "--exclude=autoheader"
EXTRA_OECONF = "--with-tls=openssl --prefix=/usr --sysconfdir=/etc"

PACKAGES =+ "${PN}-tools"

FILES_${PN} = "${libdir}/libcups3.so.* /etc/cups"
FILES_${PN}-tools = " \
	/usr/bin/* \
	/usr/share/libcups3/* \
"

# Override autotools_do_configure to prevent removal of the configure script...
autotools_do_configure() {
	DSOFLAGS="${LDFLAGS}"
	export DSOFLAGS
	oe_runconf
}

do_install () {
	oe_runmake "DSTROOT=${D}" install

	# Create /etc/cups/ssl directory
	install -d -m 755 ${D}/etc/cups
	install -d -m 700 ${D}/etc/cups/ssl
}
