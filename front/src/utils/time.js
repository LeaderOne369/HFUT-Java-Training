import {dayjs} from "element-plus";

export const timestampFormat = (t) => {
    const date = new Date(t);
    return dayjs(date).format('YYYY-MM-DD HH:mm:ss');
}

/**
 * 计算两个时间戳之间的天数
 * @param t1
 * @param t2
 */
export const calcDays = (t1, t2) => {
    return dayjs(t1).diff(dayjs(t2), 'day');
}