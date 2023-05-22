SUMMARY = "CUPS Libraries"
HOMEPAGE = "https://www.cups.org/"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "gnutls zlib"

SRC_URI = "https://github.com/OpenPrinting/cups/releases/download/v${PV}/cups-${PV}-source.tar.gz"
SRC_URI[md5sum] = "be5241e2c165eeb83b58aa32de26fb16"
SRC_URI[sha256sum] = "f03ccb40b087d1e30940a40e0141dcbba263f39974c20eb9f2521066c9c6c908"

S = "${WORKDIR}/cups-${PV}"

inherit autotools-brokensep binconfig pkgconfig

CLEANBROKEN = "1"
EXTRA_AUTORECONF += "--exclude=autoheader"
EXTRA_OECONF = "--with-tls=gnutls --disable-gssapi --disable-relro --sysconfdir=/etc --with-components=libcupslite"

FILES_${PN} = "${libdir}/libcups.so.* /etc/cups"

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
