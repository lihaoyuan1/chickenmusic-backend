var Cg7Z = function(iw0x) {
    if (iw0x < -128) {
        return Cg7Z(128 - (-128 - iw0x))
    } else if (iw0x >= -128 && iw0x <= 127) {
        return iw0x
    } else if (iw0x > 127) {
        return Cg7Z(-129 + iw0x - 127)
    } else {
        throw new Error("1001")
    }
};
var cuu4y = function(iw0x, bi8a) {
    return Cg7Z(iw0x + bi8a)
};
var ctZ4d = function(bcu5z, bkT7M) {
    if (bcu5z == null) {
        return null
    }
    if (bkT7M == null) {
        return bcu5z
    }
    var rt3x = [];
    var ctP4T = bkT7M.length;
    for (var i = 0, bs8k = bcu5z.length; i < bs8k; i++) {
        rt3x[i] = cuu4y(bcu5z[i], bkT7M[i % ctP4T])
    }
    return rt3x
};
var ctO4S = function(bcv5A) {
    if (bcv5A == null) {
        return bcv5A
    }
    var rt3x = [];
    var ctA4E = bcv5A.length;
    for (var i = 0, bs8k = ctA4E; i < bs8k; i++) {
        rt3x[i] = Cg7Z(0 - bcv5A[i])
    }
    return rt3x
};
var ctv4z = function(bli7b, Pj1x) {
    bli7b = Cg7Z(bli7b);
    Pj1x = Cg7Z(Pj1x);
    return Cg7Z(bli7b ^ Pj1x)
};
var bTU8M = function(Pi1x, blC7v) {
    if (Pi1x == null || blC7v == null || Pi1x.length != blC7v.length) {
        return Pi1x
    }
    var rt3x = [];
    var ctt4x = Pi1x.length;
    for (var i = 0, bs8k = ctt4x; i < bs8k; i++) {
        rt3x[i] = ctv4z(Pi1x[i], blC7v[i])
    }
    return rt3x
};
var bTS8K = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"];
var ctp4t = function(dt9k) {
    var Ko0x = [];
    Ko0x.push(bTS8K[dt9k >>> 4 & 15]);
    Ko0x.push(bTS8K[dt9k & 15]);
    return Ko0x.join("")
};
var bTN7G = function(vL5Q) {
    var bs8k = vL5Q.length;
    if (vL5Q == null || bs8k < 0) {
        return new String("")
    }
    var Ko0x = [];
    for (var i = 0; i < bs8k; i++) {
        Ko0x.push(ctp4t(vL5Q[i]))
    }
    return Ko0x.join("")
};
var bTD7w = function(bcH5M) {
    if (bcH5M == null || bcH5M.length == 0) {
        return bcH5M
    }
    var blU7N = new String(bcH5M);
    var rt3x = [];
    var bs8k = blU7N.length / 2;
    var bi8a = 0;
    for (var i = 0; i < bs8k; i++) {
        var pw2x = parseInt(blU7N.charAt(bi8a++), 16) << 4;
        var pu2x = parseInt(blU7N.charAt(bi8a++), 16);
        rt3x[i] = Cg7Z(pw2x + pu2x)
    }
    return rt3x
};
var bTC7v = function(cx8p) {
    if (cx8p == null || cx8p == undefined) {
        return cx8p
    }
    var XU3x = encodeURIComponent(cx8p);
    var vL5Q = [];
    var bTy7r = XU3x.length;
    for (var i = 0; i < bTy7r; i++) {
        if (XU3x.charAt(i) == "%") {
            if (i + 2 < bTy7r) {
                vL5Q.push(bTD7w(XU3x.charAt(++i) + "" + XU3x.charAt(++i))[0])
            } else {
                throw new Error("1009")
            }
        } else {
            vL5Q.push(XU3x.charCodeAt(i))
        }
    }
    return vL5Q
};
var csS3x = function(zd6X) {
    var bd8V = 0;
    bd8V += (zd6X[0] & 255) << 24;
    bd8V += (zd6X[1] & 255) << 16;
    bd8V += (zd6X[2] & 255) << 8;
    bd8V += zd6X[3] & 255;
    return bd8V
};
var cMz8r = function(bd8V) {
    var zd6X = [];
    zd6X[0] = bd8V >>> 24 & 255;
    zd6X[1] = bd8V >>> 16 & 255;
    zd6X[2] = bd8V >>> 8 & 255;
    zd6X[3] = bd8V & 255;
    return zd6X
};
var csQ3x = function(da9R, bms7l, bs8k) {
    var dK9B = [];
    if (da9R == null || da9R.length == 0) {
        return dK9B
    }
    if (da9R.length < bs8k) {
        throw new Error("1003")
    }
    for (var i = 0; i < bs8k; i++) {
        dK9B[i] = da9R[bms7l + i]
    }
    return dK9B
};
var bmv7o = function(da9R, bms7l, ti4m, csM3x, bs8k) {
    if (da9R == null || da9R.length == 0) {
        return ti4m
    }
    if (ti4m == null) {
        throw new Error("1004")
    }
    if (da9R.length < bs8k) {
        throw new Error("1003")
    }
    for (var i = 0; i < bs8k; i++) {
        ti4m[csM3x + i] = da9R[bms7l + i]
    }
    return ti4m
};
var csL3x = function(bs8k) {
    var bv8n = [];
    for (var i = 0; i < bs8k; i++) {
        bv8n[i] = 0
    }
    return bv8n
};
var csK3x = [82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125];
var Pd1x = 64;
var bcL5Q = 64;
var bTv7o = 4;
var csI3x = function(rU3x) {
    var bTt7m = [];
    if (rU3x == null || rU3x == undefined || rU3x.length == 0) {
        return csL3x(bcL5Q)
    }
    if (rU3x.length >= bcL5Q) {
        return csQ3x(rU3x, 0, bcL5Q)
    } else {
        for (var i = 0; i < bcL5Q; i++) {
            bTt7m[i] = rU3x[i % rU3x.length]
        }
    }
    return bTt7m
};
var csD3x = function(bcU5Z) {
    if (bcU5Z == null || bcU5Z.length % Pd1x != 0) {
        throw new Error("1005")
    }
    var bmX8P = [];
    var bi8a = 0;
    var csC3x = bcU5Z.length / Pd1x;
    for (var i = 0; i < csC3x; i++) {
        bmX8P[i] = [];
        for (var j = 0; j < Pd1x; j++) {
            bmX8P[i][j] = bcU5Z[bi8a++]
        }
    }
    return bmX8P
};
var csB3x = function(bTr7k) {
    var pw2x = bTr7k >>> 4 & 15;
    var pu2x = bTr7k & 15;
    var bi8a = pw2x * 16 + pu2x;
    return csK3x[bi8a]
};
var bTq7j = function(bng8Y) {
    if (bng8Y == null) {
        return null
    }
    var bTp7i = [];
    for (var i = 0, bs8k = bng8Y.length; i < bs8k; i++) {
        bTp7i[i] = csB3x(bng8Y[i])
    }
    return bTp7i
};
var bTo7h = function(OI1x, rU3x) {
    if (OI1x == null) {
        return null
    }
    if (OI1x.length == 0) {
        return []
    }
    if (OI1x.length % Pd1x != 0) {
        throw new Error("1005")
    }
    rU3x = csI3x(rU3x);
    var bnm8e = rU3x;
    var bnn8f = csD3x(OI1x);
    var WF3x = [];
    var csq3x = bnn8f.length;
    for (var i = 0; i < csq3x; i++) {
        var bnq8i = bTq7j(bnn8f[i]);
        bnq8i = bTq7j(bnq8i);
        var bns8k = bTU8M(bnq8i, bnm8e);
        var csk3x = ctZ4d(bns8k, ctO4S(bnm8e));
        bns8k = bTU8M(csk3x, rU3x);
        bmv7o(bns8k, 0, WF3x, i * Pd1x, Pd1x);
        bnm8e = bnn8f[i]
    }
    var bTi7b = [];
    bmv7o(WF3x, WF3x.length - bTv7o, bTi7b, 0, bTv7o);
    var bs8k = csS3x(bTi7b);
    if (bs8k > WF3x.length) {
        throw new Error("1006")
    }
    var rt3x = [];
    bmv7o(WF3x, 0, rt3x, 0, bs8k);
    return rt3x
};
var crZ3x = function(bcZ5e, K7D) {
    if (bcZ5e == null) {
        return null
    }
    var bTg7Z = new String(bcZ5e);
    if (bTg7Z.length == 0) {
        return []
    }
    var OI1x = bTD7w(bTg7Z);
    if (K7D == null || K7D == undefined) {
        throw new Error("1007")
    }
    var rU3x = bTC7v(K7D);
    return bTo7h(OI1x, rU3x)
};
crV3x = function(bcZ5e, K7D) {
    var bnI8A = crZ3x(bcZ5e, K7D);
    var Hw9n = new String(bTN7G(bnI8A));
    var zZ7S = [];
    var bnM8E = Hw9n.length / 2;
    var bi8a = 0;
    for (var i = 0; i < bnM8E; i++) {
        zZ7S.push("%");
        zZ7S.push(Hw9n.charAt(bi8a++));
        zZ7S.push(Hw9n.charAt(bi8a++))
    }
    return zZ7S.join("")
};
bUt8l = function(bnN8F, K7D) {
    return crU3x(cDC6w(bnN8F), K7D)
}
;
crU3x = function(bnN8F, K7D) {
    var bnI8A = bTo7h(bnN8F, bTC7v(K7D));
    var Hw9n = new String(bTN7G(bnI8A));
    var zZ7S = [];
    var bnM8E = Hw9n.length / 2;
    var bi8a = 0;
    for (var i = 0; i < bnM8E; i++) {
        zZ7S.push("%");
        zZ7S.push(Hw9n.charAt(bi8a++));
        zZ7S.push(Hw9n.charAt(bi8a++))
    }
    return zZ7S.join("")
};
var bXO8G = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
    , Xw3x = {}
    , JT0x = {};
for (var i = 0, l = bXO8G.length, c; i < l; i++) {
    c = bXO8G.charAt(i);
    Xw3x[i] = c;
    JT0x[c] = i
}
var cDJ6D = function(iM1x) {
    var r7k = 0, c, m7f = [];
    while (r7k < iM1x.length) {
        c = iM1x[r7k];
        if (c < 128) {
            m7f.push(String.fromCharCode(c));
            r7k++
        } else if (c > 191 && c < 224) {
            m7f.push(String.fromCharCode((c & 31) << 6 | iM1x[r7k + 1] & 63));
            r7k += 2
        } else {
            m7f.push(String.fromCharCode((c & 15) << 12 | (iM1x[r7k + 1] & 63) << 6 | iM1x[r7k + 2] & 63));
            r7k += 3
        }
    }
    return m7f.join("")
};
var cDH6B = function() {
    var hR0x = /\r\n/g;
    return function(i7b) {
        i7b = i7b.replace(hR0x, "\n");
        var m7f = []
            , nl2x = String.fromCharCode(237);
        if (nl2x.charCodeAt(0) < 0)
            for (var i = 0, l = i7b.length, c; i < l; i++) {
                c = i7b.charCodeAt(i);
                c > 0 ? m7f.push(c) : m7f.push(256 + c >> 6 | 192, 256 + c & 63 | 128)
            }
        else
            for (var i = 0, l = i7b.length, c; i < l; i++) {
                c = i7b.charCodeAt(i);
                if (c < 128)
                    m7f.push(c);
                else if (c > 127 && c < 2048)
                    m7f.push(c >> 6 | 192, c & 63 | 128);
                else
                    m7f.push(c >> 12 | 224, c >> 6 & 63 | 128, c & 63 | 128)
            }
        return m7f
    }
}();
var OH1x = function(iM1x) {
    var r7k = 0
        , m7f = []
        , eM9D = iM1x.length % 3;
    if (eM9D == 1)
        iM1x.push(0, 0);
    if (eM9D == 2)
        iM1x.push(0);
    while (r7k < iM1x.length) {
        m7f.push(Xw3x[iM1x[r7k] >> 2], Xw3x[(iM1x[r7k] & 3) << 4 | iM1x[r7k + 1] >> 4], Xw3x[(iM1x[r7k + 1] & 15) << 2 | iM1x[r7k + 2] >> 6], Xw3x[iM1x[r7k + 2] & 63]);
        r7k += 3
    }
    if (eM9D == 1)
        m7f[m7f.length - 1] = m7f[m7f.length - 2] = "=";
    if (eM9D == 2)
        m7f[m7f.length - 1] = "=";
    return m7f.join("")
};
var bXN8F = function() {
    var tz4D = /\n|\r|=/g;
    return function(i7b) {
        var r7k = 0
            , m7f = [];
        i7b = i7b.replace(tz4D, "");
        for (var i = 0, l = i7b.length; i < l; i += 4)
            m7f.push(JT0x[i7b.charAt(i)] << 2 | JT0x[i7b.charAt(i + 1)] >> 4, (JT0x[i7b.charAt(i + 1)] & 15) << 4 | JT0x[i7b.charAt(i + 2)] >> 2, (JT0x[i7b.charAt(i + 2)] & 3) << 6 | JT0x[i7b.charAt(i + 3)]);
        var bs8k = m7f.length
            , eM9D = i7b.length % 4;
        if (eM9D == 2)
            m7f = m7f.slice(0, bs8k - 2);
        if (eM9D == 3)
            m7f = m7f.slice(0, bs8k - 1);
        return m7f
    }
}();
cKV8N = function(i7b) {
    return cDJ6D(bXN8F(i7b))
};
cDC6w = function(i7b) {
    var iM1x = bXN8F(i7b), dx9o = iM1x.length, iO1x;
    var r7k = 0;
    while (iO1x = iM1x[r7k]) {
        if (iO1x > 128) {
            iM1x[r7k] = iO1x - 256
        }
        r7k++
    }
    return iM1x
};
cDz6t = function(i7b) {
    try {
        return window.btoa(i7b)
    } catch (ex) {
        return OH1x(cDH6B(i7b))
    }
};
cDC6w = function(i7b) {
    var iM1x = bXN8F(i7b), dx9o = iM1x.length, iO1x;
    var r7k = 0;
    while (iO1x = iM1x[r7k]) {
        if (iO1x > 128) {
            iM1x[r7k] = iO1x - 256
        }
        r7k++
    }
    return iM1x
};
cnj2x = bUt8l;
bUt8l = function(bnN8F, K7D) {
    return crU3x(cDC6w(bnN8F), K7D)
};
function getResult(C7v, param){
    return decodeURIComponent(cnj2x(C7v, param))
}
